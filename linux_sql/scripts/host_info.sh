#!/bin/bash

# save CLI arguments in variables
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# save postgres instance password in an environment variable.
export PGPASSWORD=$psql_password

# parse lscpu command output in a variable.
lscpu_out=`lscpu`

# parse cat /proc/meminfo command output in a variable.
memory_info=`cat /proc/meminfo`

#current timestamp in `YYYY-MM-DD hh:mm:ss` format
current_time_UTC=`date +%F" "%T`


# parse linux node hardware information into meaningful variables.
hostname=$psql_host
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "Model name:" | awk '{print $3" " $4" " $5}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out"  | egrep "L2 cache:" | awk '{print $3}' | sed 's/[^0-9]*//g' | xargs)
total_mem=$(echo "$memory_info"  | egrep "MemTotal:" | awk '{print $2}' | sed 's/[^0-9]*//g' | xargs)
timestamp=$current_time_UTC

# Insert linux node information in host_info table.
insert_stmt="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, total_mem, timestamp)
VALUES ('$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$l2_cache', '$total_mem', '$timestamp');"

# Connect to psql instance and execute insert query.
psql -h "$psql_host" -p "$psql_port" -d "$db_name" -U "$psql_user" -c "$insert_stmt"

exit 0