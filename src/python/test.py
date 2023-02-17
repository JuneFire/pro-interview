import sys
def sayhello(path):
    print(path)
    # 参数从下标1开始取
    # print(int(sys.argv[1]) + int(sys.argv[2]))
    return "hello"


if __name__ == '__main__':
    sayhello(sys.argv[1])