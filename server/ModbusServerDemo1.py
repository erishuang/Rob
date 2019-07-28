import time
import sys
import logging
import threading
import modbus_tk
import modbus_tk.defines as cst
import modbus_tk.modbus as modbus
import modbus_tk.modbus_tcp as modbus_tcp

import random

LOGGER = modbus_tk.utils.create_logger(name="console", record_format="%(message)s")
if __name__ == "__main__":
    try:
        SERVER = modbus_tcp.TcpServer(address="127.0.0.1", port=502) # connection set up
        LOGGER.info("running...")
        
        SERVER.start() # server started
        LOGGER.info("server started")
        
        SLAVE1 = SERVER.add_slave(1)
        SLAVE1.add_block('A', cst.HOLDING_REGISTERS, 0, 4)  # add block function
        LOGGER.info("SLAVE1 add_slave")
        
        SLAVE1.set_values('A', 0, 10*[0])  # set value function
        
        LOGGER.info("SLAVE1 set_values")
        
    finally:
        SERVER.stop()
