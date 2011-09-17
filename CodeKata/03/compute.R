# run Rscript compute.R

#########################
# the question 1: sizes
#########################
input <- c(1000, 1000000, 1000000000, 1000000000000, 8000000000000)
for(num in input)
{
rl = log2(num)
n = floor(rl) + 1 # +1 for 2^0
print(n) 
cat(sprintf("We need %d bits for %.0e\n", n, num))
}

########################
# the question 2: addresses
########################
numOfRes = 20000;
char_size = 1 # 1 byte
name_field = 10 + 10
address_field = 50
phone_nr = 9

space_byte = numOfRes * (name_field + address_field + phone_nr) * char_size
space_mega = floor(space_byte / (2^20) )
cat(sprintf("Bits required for storing contact data %d bytes: %d M\n\n", space_byte, space_mega  ))

######################
# the question 3: binary tree
###################### 
n = 1000000
size_int = 4
# n = 2^(h+1) - 1
ceiling(log2(n + 1)) -> h

print(h)

print("32 bit memory, so we need 32 bits for a integer and 2x 32 bit for left and right node")

mem <- n * (4 + 4 + 4)
mem_with_align <- n * (4 + 4 + 4 +4)
cat(sprintf("We need up to %d bytes to store %d in a binary tree or %d with 16 byte alignment \n\n", mem, n, mem_with_align))

####################
# How fast
####################
num_pages = 1200
chars_per_page = 60 * 70
bytes = 1200 * chars_per_page
# 
modem_byte_per_sec = 56000 / 8
time = ceiling(bytes / modem_byte_per_sec)
cat(sprintf("Transmitting would take %d seconds:  %d minutes\n\n"  , time, ceiling(time/60) ))

#################
# binary search
#################
num_of_comp_1 = ceiling(log2(10000))
num_of_comp_2 = ceiling(log2(100000))
num_of_comp_3 = ceiling(log2(1000000))

#print(num_of_comp_1)
#print(num_of_comp_2)

#t_per_comp_1 = num_of_comp_1 / 4.5
#t_per_comp_2 = num_of_comp_2 / 6.5
#print(t_per_comp_1)
#print(t_per_comp_2)

# let's treat a code as a log function
#  y = a * log2(n) + b
#  4.5 = a * log(10000) + b 
#- 6.5 = a * log(100000) + b
-1.5 / (log2(10000) - log2(100000)) -> a
b = 4.5 - a * log2(10000)

time = a * log2(1000000) + b 

cat(sprintf("We would need  %.2f ms\n\n", time))

###############
# brute force attack
###############
passw_length = 16 
possible_chars = 96
num_of_combinations =  (choose(96, 1))^16
print(num_of_combinations)
print(num_of_combinations / (1000 * 60 * 60 * 24 * 366))
cat(sprintf(  "To perform a brute force attack we need %d ms :  %d hours",  num_of_combinations,  floor(num_of_combinations / (1000 * 60 * 60)) )



