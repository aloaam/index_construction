CREATE EXTENSION IF NOT EXISTS tablefunc;

CREATE OR REPLACE VIEW stock_close_prices_view AS
SELECT *
FROM crosstab(
             'SELECT date, ticker, close
              FROM   alo.stock_close_prices
              ORDER  BY 1,2'
         , $$VALUES ('TWTR'), ('AAPL'), ('WYNN'), ('RDHL'), ('TBLT'), ('ITRM'), ('TTOO'), ('BABA'), ('MARK'), ('TSLA')$$
         )
         AS ct ("date" date,
                "TWTR" double precision,
                "AAPL" double precision,
                "WYNN" double precision,
                "RDHL" double precision,
                "TBLT" double precision,
                "ITRM" double precision,
                "TTOO" double precision,
                "BABA" double precision,
                "MARK" double precision,
                "TSLA" double precision
        );