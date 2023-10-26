SELECT A.AUTHOR_ID, B.AUTHOR_NAME, A.CATEGORY, SUM(A.PRICE * C.SALES) AS TOTAL_SALES
FROM BOOK A, AUTHOR B , BOOK_SALES C
WHERE A.AUTHOR_ID = B.AUTHOR_ID AND A.BOOK_ID = C.BOOK_ID AND C.SALES_DATE LIKE '%2022-01%'
GROUP BY A.CATEGORY, B.AUTHOR_NAME
ORDER BY A.AUTHOR_ID ASC, A.CATEGORY DESC