#!/usr/bin/ruby -w

# define a class
class Box

  # Initialize our class variables
   @@count = 0

   # constructor method
   def initialize(w,h)
      @width, @height = w, h
   end

   # instance method by default it is public
   def getArea
      getWidth() * getHeight
   end

   # define private accessor methods
   def getWidth
      @width
   end
   def getHeight
      @height
   end

   # setter methods
   def setWidth=(value)
      @width = value
   end
   def setHeight=(value)
      @height = value
   end

   # make them private
   private :getWidth, :getHeight

   # instance method to print area
   def printArea
      @area = getWidth() * getHeight
      puts "Big box area is : #@area"
   end
   # make it protected
   protected :printArea

   # class method
   def self.printCount()
      puts "Box count is : #@@count"
   end

end

# define a subclass
class BigBox < Box

   # add a new instance method
   def printArea
      @area = @width * @height
      puts "Big box area is : #@area"
   end
end

# create an object
box = Box.new(10, 20)

# call instance methods
a = box.getArea()
puts "Area of the box is : #{a}"

# try to call protected or methods
# box.printArea() , will give error
# create an object
