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
        ContentItem(13, false, false, "하 참아야 되는데","치킨 먹고 싶다 ㅜㅜㅜㅜㅜㅜ", null, "2024-10-27", 12, false),
        ContentItem(14, false, true, "배드민턴 칠 사람", "채 있는데 같이 칠 사람 있나..?", null, "2024-10-27", 5, false),
        ContentItem(15, false, true, "혹시 여드름 자국에", "효과본 시술 제품 뭐든 .. 있을까?", null, "2024-10-27", 2, false),
        ContentItem(16, false, true, "넷플 1명 구해요", "인당 4250원~~", null, "2024-10-27", 2, false),
        ContentItem(17, false, false, "윈드밀", "죽기전에 윈드밀 해보고 싶다 같이 연습할 사람 구함", null, "2024-10-27", 5, false),
        ContentItem(18, false, true, "가톨릭대말고 인서울 문과도 취업 힘들지않나?", "문과 취업 시장 힘들어서 인서울 문과생들 국비 교육 듣지않아? 반면에 이과에선 가톨릭대 공대면 대기업 목표로 하셈 공과대학 투자가 약해고 커리큘럼이 아쉬워서 그렇지 인턴 대외활동 자격증 동아리 어학 등등 쌓으면 대기업 진출 가능하다 그래도 가톨릭대 알 사람들은 다 알더라 까내리는 집단은 보통 시사 이유 게시판 유저일 가능성이 높으니까 학교 중소 많이 간다고 까야지 가대 학생들이 위축된 모습을 보고 그러는거니까 신경 쓰지말고 안그러면 위축된 모습에 더 조롱하고 학교 아웃풋을 늘릴 수 없게 만들려는 속셈이지 시사 이슈 게시판을 보셈 글 상태가 어떤지 반박시 님들 말이 다 틀림", null, "2024-10-27", 10, false),
        ContentItem(19, false, true, "히피펌", "하려고 하는데... 왜 네이버엔 죄다 망한 후기 밖에 없는걸까.... 해봐도되는걸까...또륵", null, "2024-10-27", 10, false),
        ContentItem(20, true, true, "[D-6] 농락 신입부원 모집👨‍🌾", "가톨릭대학교 유일 중앙환경동아리, 농사짓는 즐거움🌱농락입니다! 2024년 1학기 함께 할 신입부원을 모집합니다. 농락은 교내 2개의 텃밭에 농사를 하는 것을 주 활동으로 하고 있습니다. 이외에도 다양한 행사와 환경 활동을 진행합니다. 🍀활동 내용🍀 (1) 캠퍼스 속 농업활동 교내 텃밭에서 상추, 방울토마토, 감자 등 다양한 작물을 재배할 예정입니다. (2) 협업 청소년 센터 활동 부천시 산울림 센터와 협업하여 더욱 풍부하고 의미 있는 동아리 활동을 합니다. (3) 화분 판매, 다맛제 등 다양한 행사 농락에서만 진행되는 ✨️다양한 교내외 행사✨️가 진행될 예정입니다. ☘️신입부원 모집 안내☘️ 지원 대상: 성심교정 재학생 모집 기간: 2024.02.26(월)~2024.03.07(목) 신청 방법: 네이버 폼 작성 회비: 학기 당 5,000원 🍀정기모임 일시🍀 매주 6시 (월화수목 중 하루 필참) 자연을 사랑하고 환경보호에 뜻이 있는 분, 특별한 동아리 활동을 하고 싶은 분, 일상 속 잔잔한 힐링이 필요한 분들 모두 환영합니다💚 ⏩️농락 활동 더보기 ▪️기사 ▪️인스타 *기타 문의 사항 기획부장 임기환 농락 인스타 (cuk__nongrock) 에브리타임 쪽지, 댓글", R.drawable.img_example_4, "2024-10-27", 10, true),
        ContentItem(21, true, false, "다이노(Dyno) 클라이밍 동아리 1기 인원 모집합니다", "다이노란? 다이나믹 무브먼트(Dynamic Movement)를 줄여서 부르는 클라이밍 기술입니다:) 저희 다이노는 실력과 상관 없이 다이나믹한 동작들을 좋아하는 클라이머들의 모임입니다. 클라이밍을 배우고 싶은 사람도, 가르쳐주고 싶은 사람도, 같이 즐기고 싶은 사람도 환영합니다 🥰 ▫️암장 더클라임 연남점을 주암장으로 삼고 있습니다. 그 외에 더클라임 타지점이나 다이노나 런지가 땡기는 날에는 훅클도 가고 있어요 ! ▫️활동 _더클 기준 초록보라까지 다양한 실력대 회장이 훈수충이라 초보들 잘 알려줄 수 있음 _고정 참여(2주 1회) 및 번개 활동으로 자유롭게 참여 가능 ▫️모집 기간 2024년 4분기(10.0112.31) ▫️지원신청 ▫️문의사항 인스타링크 오픈카톡링크", R.drawable.img_example_5, "2024-10-27", 10, true),
        ContentItem(22, true, false, "🎏와스루🎏", "안녕하세요!☺️ 우리 소모임 와스루는 이름처럼 다양한 사람들이 얽히고 섞이는 모임입니다. 일본어를 배우고, 일본 문화에 대해 이야기를 나누며 차근차근 배워보아요!!🇯🇵 🙋대상 일본어 입문을 하고 싶은 분 간단한 일본어 회화를 원하시는분 일본어를 사랑하는 누구나 ! 히라가나 정도만 할 줄 아는분 📚활동내용 히라가나와 가타카나 익히기 문장의 구조 파악 일본영화와 애니를 통하여 자연스러운 표현 익히기 주어진 주제에 대해 짧은 대화하기 이시하라 상과 대화 (찐 현지인의 대화 및 팁 나눔!) ☑️모집기간 2024.10.18-10.29(조기마감 가능) 🙆지원방법 와스루에 지원하고 싶은 분들은 하단의 링크로 신청 부탁드립니다!! 와스루는 대학생에 의해 자치적으로 운영되며, 정치, 종교, 시민 단체와 전혀 관련이 없습니다.", R.drawable.img_example_6, "2024-10-27", 10, true),
        ContentItem(23, true, false, "🍩도넛을 사랑하는 귀욤이들 모임 🍰도욤이들🍰 2기 모집🍩", "🍩도넛을 사랑하는 귀욤이들 모임 🍰도욤이들🍰 2기 모집🍩 종로, 안국, 을지로, 마포, 합정, 망원, 성수에 있는 디저트를 다 섭렵하고싶은 빵덕후 모여라! 볼거리, 먹거리 다 할 수 있는 성지인 종로와 마포에서 함께 즐기고 디저트도 즐기는 뉴욤이! 🧀모집 대상 - 핫플에서 활동도 즐기며 2차로 디저트도 뿌실분! - 도넛, 베이커리, 디저트류를 사랑하고 진심인분! - E 와 I를 아우르는 모임장과 함께 도넛 맛집, 베이커리 즐기실분! - 활동 장소가 서울 혹은 수도권으로 한정되는 점을 유의해서 지원부탁해! 🥯활동비, 가입비 여부 - 들어가는 비용은 더치페이, 카페 비용만 각자 결제 하자! 🍰활동 내용 🥓1주차 홍대 청수당공명 🧇2주차 영화(우리가 보고싶은걸루~)+맛집탐방 🥞3주차 성수카페+맛집 🥖4주차 서울숲+라플레프루트 서울숲 🍩인스타 인스타그램 도넛🍩 도욤이들 공식 인스타 팔로우 좋아요 부탁해🤍 뉴욤이 신청서 작성 많관부🤍", R.drawable.img_example_7, "2024-10-27", 10, true),
        ContentItem(24, true, true, "GGUM 연합해커톤", "GGUM 연합해커톤을 소개합니다! GGUM 연합해커톤은 가톨릭대 내 IT 연합동아리, Groomthon_univ, GDG on Campus, UMC, 멋쟁이사자처럼 총 4개의 동아리가 주최하는 연합해커톤이에요. 해커톤 참여 시 받을 수 있는 혜택은 아래와 같아요. 🏆상장 입상한 참가자분들에게는 상장을 수여해요! 💵 후원 몬스터의 후원을 받아 참여자들에게 카페인 음료가 제공돼요!🎀 굿즈 구름톤, GDG, UMC, 멋사 동아리들의 굿즈와 일반 학우분들을 위한 컴공/개발 굿즈를 드려요! ➕ etc 4개의 동아리, IT 관련 학우분들과 네트워킹하고 함께 하나의 프로젝트를 만들 수 있어요! 행사 장소와 시간 🗓️ 일시 2024년 10월 26일(토) ~ 10월 27일(일) 26일은 오후 10시까지 진행하고 집에 갑니다. (학교에서 무박 불가) 27일 오전 9시에 팀별로 다시 모입니다! 📍 장소 가톨릭대학교 학생회관 1층, 2층 (전체 대관) 참가 신청하기 아래 링크를 눌러 Google Form을 작성하고 제출해주세요. 참가비 안내 GGUM에 해당하는 동아리원 참가자일 경우 참가비가 없습니다. 일반 학우일 경우 카카오뱅크로 10,000원을 입금해주세요!", R.drawable.img_example_8, "2024-10-27", 10, true),
        ContentItem(25, true, true, "🔥[D-1] 2024년도 불꽃 1학기 정기공연🔥", "안녕하세요! 가톨릭대학교 사회과학계열 유일 밴드 학회 불꽃입니다! 2024년 불꽃의 첫번째 정기공연을 준비했습니다❤️ 공연과 함께 낭만 챙기러 얼른 오세요!⭐️ 📍HALL 1855 학생회관 1층 소강당 ⏰ 5월 21일 화요일 18시 30분(18시 부터 입장 가능) ❗️별도의 신청 없이 자유롭게 오시면 됩니다! 많은 관심 부탁드립니다🥰", R.drawable.img_example_9, "2024-10-27", 10, true)


    )
    val items: List<ContentItem>
        get() = _items.toList() // 외부에서 읽기 전용으로 제공

    fun addItem(contentItem: ContentItem) {
        _items.add(contentItem) // 새로운 항목 추가
    }
}