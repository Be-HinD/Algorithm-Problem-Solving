SELECT a.ITEM_ID, a.ITEM_NAME
FROM ITEM_INFO a, ITEM_TREE b
WHERE a.ITEM_ID = b.ITEM_ID AND b.PARENT_ITEM_ID IS NULL
ORDER BY a.ITEM_ID;