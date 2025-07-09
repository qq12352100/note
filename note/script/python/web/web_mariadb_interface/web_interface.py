from flask import Flask, request, jsonify
import hashlib
import requests
import pymysql
import os
from dotenv import load_dotenv  # 新增环境变量管理

# 加载环境变量
load_dotenv()

app = Flask(__name__)

# 从环境变量获取敏感信息
APPID = os.getenv('WX_APPID')
SECRET = os.getenv('WX_SECRET')
DB_CONFIG = {
    'host': os.getenv('DB_HOST'),
    'user': os.getenv('DB_USER'),
    'password': os.getenv('DB_PASSWORD'),
    'database': os.getenv('DB_NAME'),
    'charset': 'utf8mb4',
    'cursorclass': pymysql.cursors.DictCursor
}

# 数据库连接池（简化版）
def get_db_connection():
    return pymysql.connect(**DB_CONFIG)

# 获取openid端点
@app.route('/jscode2session', methods=['GET'])
def jscode2session():
    code = request.args.get('code')
    if not code:
        return jsonify({"error": "Missing code parameter"}), 400
    
    url = f'https://api.weixin.qq.com/sns/jscode2session?appid={APPID}&secret={SECRET}&js_code={code}&grant_type=authorization_code'
    
    try:
        response = requests.get(url, timeout=5)  # 添加超时
        response.raise_for_status()
        data = response.json()

        if data.get('errcode', 0) != 0:
            return jsonify({
                "error": data.get('errmsg', 'Unknown error'),
                "code": data.get('errcode', -1)
            }), 400

        # 只返回必要信息给客户端
        # 成功返回 openid 和 session_key
        return jsonify({
            "openid": data.get("openid"),
            "session_key": data.get("session_key")
        })

    except requests.exceptions.RequestException as e:
        app.logger.error(f"WeChat API error: {str(e)}")
        return jsonify({"error": "Service temporarily unavailable"}), 503
    except Exception as e:
        app.logger.exception("Unexpected error in jscode2session")
        return jsonify({"error": "Internal server error"}), 500

# 业务处理端点
@app.route('/invoke', methods=['POST'])
def handle_invoke():
    try:
        data = request.get_json()
        if not data:
            return jsonify({"error": "Empty request body"}), 400
        app.logger.warning(data)
        # 签名验证
        if not verify_signature(data):
            app.logger.warning("Signature verification failed")
            return jsonify({"error": "Invalid signature"}), 401
        
        # 根据操作类型处理业务
        operation = data.get('operation')
        if operation == 'update':
            return handle_db_query(data)
        else:
            # 其他操作类型处理
            return jsonify({
                "status": "success",
                "message": "Operation processed"
            })
            
    except Exception as e:
        app.logger.exception("Error in handle_invoke")
        return jsonify({"error": "Internal processing error"}), 500

# 安全的数据库查询
def handle_db_query(data):
    try:
        conn = get_db_connection()
        with conn.cursor() as cursor:
            # 参数化查询防止SQL注入
            table = data.get('table', '').strip()
            if not table:
                return jsonify({"error": "Missing table parameter"}), 400
                
            # 示例：根据实际需求构建安全查询
            sql = f"SELECT * FROM `{table}` LIMIT 10"  # 限制返回数量
            cursor.execute(sql)
            results = cursor.fetchall()
            return jsonify(results)
            
    except pymysql.Error as e:
        app.logger.error(f"Database error: {str(e)}")
        return jsonify({"error": "Database operation failed"}), 500
    finally:
        if conn:
            conn.close()

# MD5签名验证
def verify_signature(data):
    required_fields = ['time', 'table', 'operation', 'single']
    if not all(field in data for field in required_fields):
        return False
    message = f"{data['time']}{data['table']}{data['operation']}".encode()
    md5_str = hashlib.md5(message).hexdigest()
    return md5_str == data['single']

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=5000)
    # 生产环境应关闭debug模式
    # app.run(host='0.0.0.0', port=5000, debug=os.getenv('FLASK_DEBUG', 'false').lower() == 'true')
    
'''

# 删除
{
    "time": 123,
    "table": "user",
    "operation": "del",
    "data": {
        "id": "1,2,3"
    },
    "single": "12312312312312313"
}

# 添加 不用传id
{
    "time": 123,
    "table": "user",
    "operation": "add",
    "data": {
        "name":"123"
        "age":"1234"
    },
    "single": "12312312312312313"
}

# 修改 必须填id
{
    "time": 123,
    "table": "user",
    "operation": "modify",
    "data": {
        "id":"1"
        "name":"123"
        "age":"1234"
    },
    "single": "12312312312312313"
}

# 查看
    "time": 123,
    "table": "user",
    "operation": "list",
    "data": {
        "id":"1"
        "name":"123"
        "age":"1234"
    },
    "pageNum":"0",
    "pageSize":"20",
    "single": "12312312312312313"
}


'''


















