package ggum.oo.data.service

import ggum.oo.R

object ContentList {
    val items = listOf(
        ContentItem(true, true, "UMC 모집 중", "가나다라마바사아자차카타파하 푸루루루루ㅜ루루루루ㅜ루루루루ㅜ루루룰",R.drawable.img_example_content, "2024-01-01", 3, false),
        ContentItem(true, false, "교외 제목 2", "교외 본문 2", null, "2024-02-02", 5, true),
        ContentItem(true, true, "교내 제목 3", "교내 본문 3", null, "2024-03-03", 1, false),
        ContentItem(true, false, "교외 제목 4", "교외 본문 4", R.drawable.img_example_content, "2024-04-04", 2, true),
        ContentItem(true, true, "교내 제목 5", "교내 본문 5", null, "2024-05-05", 4, true),
        ContentItem(true, false, "교외 제목 6", "교외 본문 6", R.drawable.img_example_content, "2024-06-06", 0, false),
        ContentItem(false, true, "UMC 모집 중이라는데?", "가나다라마바사아자차카타파하 푸루루루루ㅜ루루루루ㅜ루루루루ㅜ루루룰", null, "2024-01-01", 3, false),
        ContentItem(false, false, "오늘 학식", "교외 본문 2", null, "2024-02-02", 5, true),
        ContentItem(false, true, "학관 1층에 좀비있는데?", "교내 본문 3", null, "2024-03-03", 1, false),
        ContentItem(false, false, "미디어기술콘텐츠학과 짱짱맨", "교외 본문 4", null, "2024-04-04", 2, true),
        ContentItem(false, true, "CDZ 공연한다며", "교내 본문 5", null, "2024-05-05", 4, true),
        ContentItem(false, false, "우히히히ㅣ히히히히", "교외 본문 6", null, "2024-06-06", 0, false)
    )
}