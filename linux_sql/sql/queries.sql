
-- Group hosts by hardware info
SELECT
    cpu_number,
    id AS host_id,
    total_mem
FROM
    host_info
GROUP BY
    id,
    cpu_number
ORDER BY
    total_mem DESC;

-- Functions to calculate average memory usage
-- Function to round timestamp to the nearest 5 minute interval.
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS $$ BEGIN RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$ LANGUAGE PLPGSQL;

-- Function to calculate percentage of used memory.
CREATE FUNCTION calculate_used_memory_percentage(
    host_id INTEGER, memory_free INTEGER
) RETURNS NUMERIC AS $$ DECLARE total_memory INTEGER;
BEGIN
SELECT
    (total_mem / 1000) INTO total_memory
FROM
    host_info
WHERE
        id = host_id;
RETURN (total_memory - memory_free) * 100 / total_memory;
END;
$$ LANGUAGE PLPGSQL;

-- Average memory usage
SELECT
    host_id,
    (
        SELECT
            hostname
        FROM
            host_info
        WHERE
                id = host_id
    ),
    round5(timestamp) AS ts,
    AVG(
            calculate_used_memory_percentage(host_id, memory_free)
        ) AS avg_used_mem_percentage
FROM
    host_usage
GROUP BY
    host_id,
    ts;

-- Detect host failure
SELECT
    host_id,
    round5(timestamp) AS ts,
    COUNT(timestamp) AS num_data_points
FROM
    host_usage
GROUP BY
    host_id,
    ts
HAVING
        COUNT(timestamp) < 3;
