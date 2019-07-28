import sys
import struct
import modbus_tk
import modbus_tk.defines as mtk
import modbus_tk.modbus
import modbus_tk.modbus_tcp
import time
import random
import string

logger = modbus_tk.utils.create_logger(name="console", record_format="%(message)s")

try:
    server = modbus_tk.modbus_tcp.TcpServer(port=502, address='127.0.0.1', timeout_in_sec=3)
    server.start()
    slave_1 = server.add_slave(1)
    slave_1.add_block('block1', modbus_tk.defines.HOLDING_REGISTERS, 0, 11)
    slave_1.set_values('block1', 0, 10*[0])
    slave_1.set_values('block1', 10, 255)
   
    valueAll = slave_1.get_values('block1', 0, 11) #get value function
    print ('valueAll: ', valueAll)

    valueSet = slave_1.get_values('block1', 0, 1)  
    print ('valueSet:  ', valueSet)


except:
    print ('============error===========')
finally:
    print ('=========stop========')
    server.stop()
