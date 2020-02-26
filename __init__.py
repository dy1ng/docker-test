import datetime


class TcMessages:
    def __init__(self):
        self.message_body = None
        self.message_name = None
        self.flowId = None
        self.timestamp = datetime.datetime.now().strftime('%Y-%m-%dT%H:%M:%S.%f')[:-3]

    def get_time(self):
        print(self.timestamp)

    def print_message(self, msg_name, msg, flow_id=None):
        self.message_name = msg_name
        self.message_body = msg
        if flow_id:
            self.flowId = flow_id
            print('##teamcity[{} {} {} timestamp=\'{}\']'.format(self.message_name, self.message_body,
                                                             self.flowId, self.timestamp))
        else:
            print('##teamcity[{} {} timestamp=\'{}\']'.format(self.message_name, self.message_body, self.timestamp))


while __name__ == '__main__':
    m = TcMessages()
    m.get_time()
    m.print_message('setParameter', 'name=\'test_param\' value=\'updated from service message\'')
    exit(0)
