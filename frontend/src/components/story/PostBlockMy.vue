<template>
  <div>
    <!-- <b-card-group deck> -->
      <b-card header-tag="header" footer-tag="footer"> <!-- title="Title" 속성 사용 가능  -->
        <template >
          <b-card-text class="font-weight-bold">
            <!-- <span class="mr-5">뱃지 img</span> -->
            <!-- <span >용용이</span> -->
          </b-card-text>
        </template>
        <div> <!-- for 문 -->
          <!-- <b-card-img class="col-3 mb-5" src="https://placekitten.com/480/210" alt="Image" bottom></b-card-img> -->
          <ImageSlick />
        </div>
        <p>게시글 내용</p>
        <template #footer>
          <b-button class="mr-5" href="#" variant="info">상세보기</b-button>
          <!-- <b-button class="ml-5" href="#" variant="info">댓글</b-button> -->
        </template>
      </b-card>
    <!-- </b-card-group> -->
   <div>
</div>   
  </div>
</template>

<script>
import ImageSlick from '@/components/story/ImageSlick'
import axios from 'axios';
const SERVER_URL = "http://localhost:8000";

export default {
  name: 'PostBlockMy',
  components: {
    ImageSlick,
  },
  data() {
    return {
      posts:[],
      limit: 3,  //한 페이지에 노출될 게시글의 수
      offset: 0,  //게시글 번호 오프셋
    }
  },
  mounted() {
    this.getPostInfos();
  },
  methods: {
    getPostInfos(){
      axios
        .get(`${SERVER_URL}/userpost`, {
          params: {
            limit: this.limit,
            offset: this.offset
          }
      })
        .then((response) => {
          console.log(response);
        });
    }
  },
}
</script>

<style>
  
  /* 적용안됨 */
  b-card-img {
    height: 5px;
    width: 5px;
  }
</style>