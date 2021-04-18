<<<<<<< Updated upstream
=======
#!/bin/bash

# save hostname in a variable
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

export PGPASSWORD=$psql_password

lscpu_out=`lscpu`
memory_info=`cat /proc/meminfo`
#current timestamp in `2019-11-26 14:40:19` format
current_time_UTC=`date +%F" "%T`


#hardware
hostname=$psql_host
#save number of CPU to a variable
#note: `xargs` is a trick to remove leading and trailing white spaces
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "Model name:" | awk '{print $3" " $4" " $5}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out"  | egrep "L2 cache:" | awk '{print $3}' | sed 's/[^0-9]*//g' | xargs)
total_mem=$(echo "$memory_info"  | egrep "MemTotal:" | awk '{print $2}' | sed 's/[^0-9]*//g' | xargs)
timestamp=$current_time_UTC

insert_stmt="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, total_mem, timestamp)
VALUES ('$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$l2_cache', '$total_mem', '$timestamp');"

psql -h "$psql_host" -p "$psql_port" -d "$db_name" -U "$psql_user" -c "$insert_stmt"

exit 0

>>>>>>> Stashed changes
