package ggum.oo.data

import ggum.oo.R

object ContentList {
    private val _items = mutableListOf<ContentItem>(
        ContentItem(1, false, false, "GGUM 해커톤 다들 참가할거야??", "나는 이번에 안드로이드 파트로 지원하려고 하는데 같이 지원할 사람??", R.drawable.img_example_content, "2024-10-26", 0, false),
        ContentItem(
            2,
            false,
            true,
            "UMC 모집 중이라는데?",
            "관심있는 사람???",
            null,
            "2024-01-01",
            3,
            false
        ),
        ContentItem(3, false, false, "오늘 학식", "교외 본문 2", null, "2024-10-26", 5, true),
        ContentItem(
            6,
            true,
            true,
            "UMC 모집 중",
            "[ ⏰ 대규모 IT 연합 사이드 프로젝트 동아리 UMC 가톨릭대 7기 추가 모집 ⏰ ]",
            R.drawable.img_example_umc,
            "2024-10-26",
            3,
            false
        ),
        ContentItem(
            7,
            true,
            false,
            "구름톤 모집 중",
            "\uD83E\uDD81GROWL-TO-WORLD\uD83E\uDD81\n" +
                    "꿈을 위한 도전하는 분들에게 각종 프로그래밍 교육을 지원하는 국내 최대 규모의 IT 창업 연합동아리입니다!",
            R.drawable.img_example_content,
            "2024-10-26",
            5,
            true
        ),
        ContentItem(
            8,
            true,
            true,
            "GDG 모집 중",
            "안녕하세요! Google Developer Student Clubs 가톨릭대학교 3기 Member를 모집합니다! ",
            R.drawable.img_example_gdg,
            "2024-10-26",
            1,
            false
        ),
        ContentItem(
            4,
            true,
            false,
            "[D-3]\uD83E\uDD81가톨릭대학교 멋쟁이사자처럼 12기 모집\uD83E\uDD81",
            "\uD83E\uDD81GROWL-TO-WORLD\uD83E\uDD81\n" +
                    "꿈을 위한 도전하는 분들에게 각종 프로그래밍 교육을 지원하는 국내 최대 규모의 IT 창업 연합동아리입니다!",
            R.drawable.img_example_lion,
            "2024-10-26",
            2,
            true
        ),
        ContentItem(
            5,
            true,
            true,
            "숙제영화제 개최합니다!!",
            "안녕하세요. 매년 숙제영화제를 개최하고 있는 미디어기술콘텐츠학과 소모임 포롱입니다! 올해 6번째 숙제영화제를 11월 12일에 개최합니다~!!",
            null,
            "2024-10-26",
            4,
            true
        ),
        ContentItem(9, false, true, "학관 1층에 좀비있는데?", "교내 본문 3", null, "2024-10-27", 1, false),
        ContentItem(10, false, false, "미디어기술콘텐츠학과 짱짱맨", "교외 본문 4", null, "2024-10-27", 2, true),
        ContentItem(11, false, true, "CDZ 공연한다며", "교내 본문 5", null, "2024-10-27", 4, true),
        ContentItem(12, false, false, "우히히히ㅣ히히히히", "교외 본문 6", null, "2024-10-27", 0, false),

    )
    val items: List<ContentItem>
        get() = _items.toList() // 외부에서 읽기 전용으로 제공

    fun addItem(contentItem: ContentItem) {
        _items.add(contentItem) // 새로운 항목 추가
    }
}