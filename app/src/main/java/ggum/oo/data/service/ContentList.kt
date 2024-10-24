package ggum.oo.data.service

import ggum.oo.R

object ContentList {
    val items = listOf(
        ContentItem(1,false, false, "배고파요", "밥줘요밥부ㅏ주바주ㅏㅂ주ㅏㅈ", null, "2024-06-06", 0, false),
        ContentItem(2,false, true, "UMC 모집 중이라는데?", "가나다라마바사아자차카타파하 푸루루루루ㅜ루루루루ㅜ루루루루ㅜ루루룰", null, "2024-01-01", 3, false),
        ContentItem(3,false, false, "오늘 학식", "교외 본문 2", null, "2024-02-02", 5, true),
        ContentItem(6,true, true, "UMC 모집 중", "[ ⏰ 대규모 IT 연합 사이드 프로젝트 동아리 UMC 가톨릭대 7기 추가 모집 ⏰ ]",R.drawable.img_example_umc, "2024-01-01", 3, false),
        ContentItem(7, true, false, "구름톤 모집 중", "\uD83E\uDD81GROWL-TO-WORLD\uD83E\uDD81\n" +
                "꿈을 위한 도전하는 분들에게 각종 프로그래밍 교육을 지원하는 국내 최대 규모의 IT 창업 연합동아리입니다!", R.drawable.img_example_content, "2024-02-02", 5, true),
        ContentItem(8,true, true, "GDG 모집 중", "안녕하세요! Google Developer Student Clubs 가톨릭대학교 3기 Member를 모집합니다! ", R.drawable.img_example_gdsc, "2024-03-03", 1, false),
        ContentItem(4,true, false, "[D-3]\uD83E\uDD81가톨릭대학교 멋쟁이사자처럼 12기 모집\uD83E\uDD81", "\uD83E\uDD81GROWL-TO-WORLD\uD83E\uDD81\n" +
                "꿈을 위한 도전하는 분들에게 각종 프로그래밍 교육을 지원하는 국내 최대 규모의 IT 창업 연합동아리입니다!", R.drawable.img_example_lion, "2024-04-04", 2, true),
        ContentItem(5,true, true, "포롱 모집 중", "교내 본문 5", R.drawable.img_example_forlong, "2024-05-05", 4, true),
        ContentItem(9,false, true, "학관 1층에 좀비있는데?", "교내 본문 3", null, "2024-03-03", 1, false),
        ContentItem(10,false, false, "미디어기술콘텐츠학과 짱짱맨", "교외 본문 4", null, "2024-04-04", 2, true),
        ContentItem(11,false, true, "CDZ 공연한다며", "교내 본문 5", null, "2024-05-05", 4, true),
        ContentItem(12,false, false, "우히히히ㅣ히히히히", "교외 본문 6", null, "2024-06-06", 0, false)
    )
}