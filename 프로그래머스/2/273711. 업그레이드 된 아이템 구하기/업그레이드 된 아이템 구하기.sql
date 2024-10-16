SELECT A.ITEM_ID, A.ITEM_NAME, A.RARITY
FROM ITEM_INFO A, ITEM_TREE B
WHERE A.ITEM_ID = B.ITEM_ID AND B.PARENT_ITEM_ID
IN (SELECT ITEM_ID
FROM ITEM_INFO
WHERE RARITY = "RARE")
ORDER BY A.ITEM_ID DESC;

# 0,1,3,4