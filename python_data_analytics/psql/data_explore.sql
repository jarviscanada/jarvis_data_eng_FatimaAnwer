-- Show table schema
\d + retail;

-- Show first 10 rows
SELECT
    *
FROM
    retail
        limit
    10;

-- Check # of records
SELECT
    COUNT(*)
FROM
    retail;

-- number of clients (e.g. unique client ID)
SELECT
    COUNT (DISTINCT customer_id) AS "Number of Clients"
FROM
    retail;

-- invoice date range (e.g. max/min dates)
SELECT
    max(invoice_date),
    min(invoice_date)
FROM
    retail;

-- number of unique merchants
SELECT
    COUNT (DISTINCT stock_code) AS "Number of Merchants"
FROM
    retail;

-- average invoice amount excluding invoices with a negative amount
SELECT
    avg(invoice_amount)
FROM
    (
        SELECT
            sum(quantity * unit_price) AS invoice_amount,
            invoice_no
        FROM
            retail
        GROUP BY
            invoice_no
        HAVING
                sum(quantity * unit_price)> 0
    ) AS avg_invoice_amount;

-- total revenue
SELECT
    sum(invoice_amount)
FROM
    (
        SELECT
            sum(quantity * unit_price) AS invoice_amount,
            invoice_no
        FROM
            retail
        GROUP BY
            invoice_no
        HAVING
                sum(quantity * unit_price)> 0
    ) AS total_revenue;

-- total revenue by YYYYMM
SELECT
    (
                extract(
                        YEAR
                        FROM
                        invoice_date
                    )* 100 + extract(
                        MONTH
                        FROM
                        invoice_date
                    )
        ) AS yyyymm,
    sum(invoice_amount)
FROM
    (
        SELECT
            sum(quantity * unit_price) AS invoice_amount,
            invoice_no,
            invoice_date
        FROM
            retail
        GROUP BY
            (invoice_no, invoice_date)
        HAVING
                sum(quantity * unit_price)> 0
    ) AS total_revenue
GROUP BY
    yyyymm
ORDER BY
    yyyymm;

