function solution(enroll, referral, seller, amount) {
    // 이름 - 번호
    const referralMap = {}
    // 판매자 - 추천인
    const recommendMap = {}
    
    const N = enroll.length
    const money = Array(N).fill(0)
    
    for (let i=0; i<N; i++) {
        referralMap[enroll[i]] = i
        recommendMap[enroll[i]] = referral[i]
    }
    
    for (let i=0; i<seller.length; i++) {
        
        const sellerWho = seller[i]
        const sellerMoney = amount[i] * 100
        
        let curWho = sellerWho
        let curMoney = sellerMoney
        
        while (curMoney >= 1 && curWho != "-") {
            
            const idx = referralMap[curWho]
            // 본인이 가져갈 돈
            
            const recommed = recommendMap[curWho] // 추천해준사람
            
            if (Math.floor(curMoney * 0.1) === 0 ) {
                money[idx] += curMoney;
                break
            }
            
            const remain = Math.floor(curMoney * 0.1)
            const ownMoney = curMoney - remain
            money[idx] += ownMoney
            curMoney = remain
            curWho = recommed
            
        }
    }
    
    return money
}