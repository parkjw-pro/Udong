<template>
  <div>
    <h2>개발진 소개</h2>
    <div class="pt-5 mt-5">
      <b-card-group>
        <!-- 강용욱 -->
        <b-card
          title="강용욱"
          img-src="https://lh3.googleusercontent.com/proxy/IHWwSa5y-TezUCCdHTiehRarVz1AqHwvVHK0UzdHiKHEmSjBkdm5lcCZLbR6cMooEN4QTa8UAB06Z9jY3j3JzjgWotkBrHP6Em4FG0MbnyRTSuC1CQ"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 30rem;"
          class="mb-2 mx-2"
        >
          <b-card-text>
            어록: "결쿡엔"(외국인 아님)
            <br>
            <br>
            특징: 프로질문러. 이해가 안되면 안 넘어감.
            <br>
            다른 사람도 얄짤없음.
            <br>
            하지만 그만큼 설명을 잘해줌.
          </b-card-text>

          <b-button href="#" variant="primary" v-b-modal="'yong-modal'">자세히 탐구하기</b-button>
          <b-modal id="yong-modal">4팀의 멋있는 에이스 <br>캉!룡용용!욱!!</b-modal>
        </b-card>
        <!-- 박종원 -->
        <b-card
          title="박종원"
          img-src="https://funshop.akamaized.net/abroad/034/17450/Blacn%202_3.png"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 30rem;"
          class="mb-2 mx-2"
        >
          <b-card-text>
            어록: "~인데 그치?" (완곡한 어법 사용)
            <br>
            <br>
            특징: 웹엑스에서 맨날 얼굴이 가려져있어서 
            <br>
            아직까지 얼굴을 제대로 본 적이 없음.
            <br>
            프로젝트 끝날때까지 얼굴 모를것 같음.
            <br>
            은둔 고수
          </b-card-text>

          <b-button href="#" variant="primary" v-b-modal="'jong-modal'">자세히 탐구하기</b-button>
          <b-modal id="jong-modal">4팀 므째이 두째이 세째이 <br> 파크!종!워이~~</b-modal>
        </b-card>

        <b-card
          title="이규용"
          img-src="https://i.pinimg.com/736x/95/6c/1d/956c1d1df35320eb4ee1ff967a489474.jpg"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 30rem;"
          class="mb-2 mx-2"
        >
          <b-card-text>
            어록: '좀 구려'
            <br>
            <br>
            특징: 히피펌 사자머리
            <br>
            팀내 디자인, UCC 등 온갖 잡다한
            <br>
            업무를 다 도맡아서 함.
            <br>
            팀장이 개인적으로 미안해함.
            <br>
            (그래서 홍설 사진으로)
          </b-card-text>

          <b-button href="#" variant="primary" v-b-modal="'yongyong-modal'">자세히 탐구하기</b-button>
          <b-modal id="yongyong-modal">4팀의 든든한 막내 <br> 리!규!용용용용용용용 용용용용용용용용ㅇ!</b-modal>
        </b-card>
      </b-card-group>
      <b-card-group>
        <!-- 이규용 -->
        
        <!-- 이송영 -->
        <b-card
          title="이송영"
          img-src="https://t1.daumcdn.net/cfile/blog/257A65445542EA850F"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 30rem;"
          class="mb-2 mx-2"
        >
          <b-card-text>
            어록: "오케이오케이"
            <br>
            <br>
            특징: 그냥 갑자기 혼자서 노래부름
            <br>
            입을 잘 텀
            <br>
            아침부터 말이 많음
            <br>
            하지만 사실 분위기를 띄우려는
            <br>
            부담감을 가지고 있음
          </b-card-text>

          <b-button href="#" variant="primary" v-b-modal="'song-modal'">자세히 탐구하기</b-button>
          <b-modal id="song-modal">그냥 4팀의 송!제!로!</b-modal>
        </b-card>
        <!-- 우진하 -->
        <b-card
          title="우진하"
          img-src="https://img.sbs.co.kr/newsnet/etv/upload/2013/11/15/30000333550_700.jpg"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 22rem;"
          class="mb-2 mx-2"
        >
          <b-card-text>
            어록: '하... 나 출첵은 했는데 다시 잤어..'
            <br>
            <br>
            특징: 프로지각러
            <br>
            1주일에 기본 4-5번 지각
            <br>
            덕분에 매일 프로님, 컨설턴트님, 코치님과
            <br>
            연락하는 사이가 됨.
            <br>
            그러다가 갑자기 탈싸피함.
            <br>
            (?????)
          </b-card-text>

          <b-button href="#" variant="primary" v-b-modal="'woo-modal'">자세히 탐구하기</b-button>
          <b-modal id="woo-modal">4팀의 변!절!자! 우~~~~~~~~~~~ <br> 진!하!</b-modal>
        </b-card>
      </b-card-group>
     
    </div>
  </div>
</template>

<script>
export default {
  name: 'Developers',
  data: function () {
    return {
      
    }
  },
}
</script>

<style>

</style>