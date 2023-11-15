SELECT MONTH(A.START_DATE) AS MONTH, CAR_ID, COUNT(CAR_ID) RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY A
WHERE MONTH(A.START_DATE) BETWEEN 8 AND 11 
            AND CAR_ID IN (SELECT CAR_ID
                 FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                 WHERE MONTH(START_DATE) BETWEEN 8 AND 11
                 GROUP BY CAR_ID
                 HAVING COUNT(CAR_ID) >= 5)
            AND CAR_ID NOT IN (SELECT CAR_ID
                              FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                              WHERE MONTH(START_DATE) BETWEEN 8 AND 11
                              GROUP BY MONTH(START_DATE), CAR_ID
                              HAVING COUNT(CAR_ID) = 0
                              )
GROUP BY MONTH, CAR_ID
ORDER BY MONTH ASC, CAR_ID DESC
