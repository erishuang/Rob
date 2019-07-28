from flask import Flask, jsonify, request

app = Flask(__name__)
waiting_list = []


# accounts = [{'name':'Billy', 'balance':'450.0'}]

# @app.route("/accounts", methods=["GET"])
# def get_accounts():
#     # return "odk, 你咖啡好了\n"
#     return jsonify(accounts)

@app.route("/")
def hello_world():
    return "odk, 你咖啡好了\n"
    # return jsonify(accounts)

@app.route("/test", methods=["POST"])
def get_order():
    order = request.data
    waiting_list.append(order.decode('utf-8'))
    print(waiting_list)
    n=len(waiting_list)
    print(n)
    #return '当前正在等待的订单：'+' '.join(waiting_list)
    if n == 1:
        return '正在处理您的订单,'+'\n'+'您的序号：'+str(n)
    else:
        return '当前正在等待的订单数：'+str(n-1)+'\n'+'您的序号：'+str(n)

# @app.route("")





if __name__ == '__main__':
    app.run(host='0.0.0.0', port=6666)
