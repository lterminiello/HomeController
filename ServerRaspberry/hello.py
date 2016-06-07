class Hello:
    __gui = None
    no = ""

    def __init__(self, gui):
        self.__gui = gui
 	self.no = ""
    def holi(self):
        print 'Hello world!'
	self.no = self.no + "hola "
	return self.no
