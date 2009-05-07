require 'D:\mycode\restair\bin\restair.jar'
require 'D:\0stuff\db4o-6.1\lib\db4o-6.1-java5.jar'
include_class 'restair.data.RealRepository'
rep = RealRepository.new()
rep.nextBookingReference()
include_class 'restair.domain.Flight'
include_class 'restair.domain.Place'
b2b = Flight.new(rep, "101", Place::MUMBAI, Place::BANGALORE, java.util.Date.new(), 75)
rep.createFlight(b2b)


        /** Open and keep the db4o object container. */
        Configuration config = Db4o.configure();
        config.updateDepth(2);
        this.db = Db4o.openFile(System.getProperty("user.home")
                + File.separator + "restair.dbo");

require java
require 'D:\mycode\restair\bin\restair.jar'
require 'D:\0stuff\db4o\objectmanager-6.1\lib\db4o-6.1-java5.jar'
include_class com.db4o.Db4o
db = Db4o.openClient("localhost",7001,"db4o","db4o")

include_class 'restair.domain.Flight'
