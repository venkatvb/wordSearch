def getName
	        o = [('a'..'z')].map { |i| i.to_a }.flatten
		        string = (0...10).map { o[rand(o.length)] }.join
			        return string
end
count = 1000000
file = File.open("input.txt", "w")
file.write(count.to_s + "\n")
(0..count).each do |i|
	file.write(getName + "\n")
end

file.close unless file.nil?

