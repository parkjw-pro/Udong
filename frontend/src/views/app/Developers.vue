<template>
  <div class="mt-5">
    <h1 style="font-family: 'Nanum Pen Script', cursive;">Developers</h1>
    <div class="mt-5 mx-5">
      <b-card-group>
        <!-- 강용욱 -->
        <b-card
          title="강용욱"
          id="developers_card"
          :img-src="require('@/assets/app/developers/santa.jpg')"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 21rem; font-family: 'Hanna', sans-serif;"
          class="mb-2 mx-2"
        >
          <b-card-text>
            <h3 style="font-family: 'Nanum Pen Script', cursive;">BE, FE, DB</h3>
            <hr>
            <div>어록: '결쿡엔'(외국인 아님)</div>
            <hr>
            <div>#우수한개발자</div>
            <div>#열정적</div>
            <div>#꼼꼼함</div>
          </b-card-text>
          <br>
          <b-button href="#" variant="primary" size="sm" v-b-modal="'yong-modal'">자세히 탐구하기</b-button>
          <b-modal title="4팀 에이스 강용욱" id="yong-modal">"끝까지 재밌었다!"</b-modal>
        </b-card>
        <!-- 박종원 -->
        <b-card
          title="박종원"
          id="developers_card"
          :img-src="require('@/assets/app/developers/gat.jpg')"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 21rem; font-family: 'Hanna', sans-serif;"
          class="mb-2 mx-2"
        >
          <b-card-text>
            <h3 style="font-family: 'Nanum Pen Script', cursive;">BE, DB, Server</h3>
            <hr>
            <div>어록: (주로 완곡한 어법 사용)</div>
            <hr>
            <div>#능력자</div>
            <div>#중재</div>
            <div>#노력형</div>
          </b-card-text>
          <br>
          <b-button href="#" variant="primary" size="sm" v-b-modal="'jong-modal'">자세히 탐구하기</b-button>
          <b-modal title="4팀 므째이므째이 박종원" id="jong-modal">"재밌었다!"</b-modal>
        </b-card>

        <!-- 이규용 -->
        <b-card
          title="이규용"
          id="developers_card"
          :img-src="require('@/assets/app/developers/rudolf.jpg')"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 21rem; font-family: 'Hanna', sans-serif;"
          class="mb-2 mx-2"
        >
          <b-card-text>
            <h3 style="font-family: 'Nanum Pen Script', cursive;">BE, DB, UCC</h3>
            <hr>
            <div>어록: '좀 구려'</div>
            <hr>
            <div>#뛰어난개발역량</div>
            <div>#소통</div>
            <div>#다재다능</div>
          </b-card-text>
          <br>
          <b-button href="#" variant="primary" size="sm" v-b-modal="'yongyong-modal'">자세히 탐구하기</b-button>
          <b-modal id="yongyong-modal" title="4팀의 든든한 막내 이규용">재밌었다.</b-modal>
        </b-card>

         <!-- 이송영 -->
        <b-card
          title="이송영"
          id="developers_card"
          :img-src="require('@/assets/app/developers/mask.jpg')"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 21rem; font-family: 'Hanna', sans-serif;"
          class="mb-2 mx-2"
        >
          <b-card-text>
            <h3 style="font-family: 'Nanum Pen Script', cursive;">FE, Presentation</h3>
            <hr>
            <div>어록: '오케이오케이'</div>
            <hr>
            <div>#팀장</div>
            <div>#발표자</div>
            <div>#개발재밌어</div>
          </b-card-text>
          <br>
          <b-button href="#" variant="primary" size="sm" v-b-modal="'song-modal'">자세히 탐구하기</b-button>
          <b-modal id="song-modal" title="4팀의 송제로 이송영">재밌었다.</b-modal>
        </b-card>

        <!-- 우진하 -->
        <b-card
          title="우진하"
          id="developers_card"
          :img-src="require('@/assets/app/developers/gasmask.jpg')"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 21rem; font-family: 'Hanna', sans-serif;"
          class="mb-2 mx-2"
        >
          <b-card-text>
            <h3 style="font-family: 'Nanum Pen Script', cursive;">FE, Semiconductor</h3>
            <hr>
            <div>어록: '하 나 출첵은 했는데 다시 잤어..'</div>
            <hr>
            <div>#전지전자과</div>
            <div>#성남통</div>
            <div>#장래가기대됨</div>
          </b-card-text>
          <br>
          <b-button href="#" variant="primary" size="sm" v-b-modal="'woo-modal'">자세히 탐구하기</b-button>
          <b-modal id="woo-modal" title="4팀의 변절자 하지만 장래가 기대되는 우진하">재밌었다.</b-modal>
        </b-card>
      </b-card-group>
      <b-card-group>
        
       
        
      </b-card-group>
      
      <div style="font-family: 'Nanum Pen Script', cursive;">
        <h1 class="py-5 mt-5">Development Tools</h1>

        <b-jumbotron header="Programming Language">
          <br>
          <h2><span id="UL">#Java #Javascript #SQL #CSS</span></h2>
        </b-jumbotron>
        
        <b-jumbotron header="Environment">
          <br>
          <h3><span id="UL2">#openjdk 11.0.9 #npm 6.14.10 #vue/cli 4.5.19 #MySQL 8.0.22</span></h3>
        </b-jumbotron>

        <b-jumbotron header="Framework">
          <br>
          <h3><span id="UL">#Spring Boot #Bootstrap</span></h3>
        </b-jumbotron>

        <b-jumbotron header="Programs">
          <br>
          <h2><span id="UL2">#VSCode #Swagger #GitLab #Jira #AWS EC2 #MobaXterm</span></h2>
        </b-jumbotron>



      </div>
      <!-- <coverflow :coverList="coverList" :coverWidth="260" :index="2"></coverflow> -->
    </div>
  </div>
</template>

<script>
export default {
  name: 'Developers',
  data: function () {
    return {
      coverList: [
        {
          cover: 'http://ogu51f989.bkt.clouddn.com/react.png',
          title: 'Vue'
        }, {
          cover: 'http://ogu51f989.bkt.clouddn.com/angular.png',
          title: 'Node'
        }, {
          cover: 'http://ogu51f989.bkt.clouddn.com/vue.png',
          title: 'Spirng Boot'
        }, {
          cover: 'http://ogu51f989.bkt.clouddn.com/webpack.png',
          title: 'MySQL'
        },
      ]
    }
  },
}
</script>

<style>
#developers_card {
  /* min-width: 20em;
  min-height: 15em;
  max-width: 20em;
  max-height: 15em; */
}

#UL {
  background: linear-gradient(to top, #FFE400 50%, transparent 50%);
}

#UL2 {
  background-color: #FF00DD;
  color: #fff;
}
</style>