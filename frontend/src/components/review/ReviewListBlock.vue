<template>
  <div>

  </div>
</template>

<script>
export default {
  name: 'ReviewListBlock',
  data: function () {

  },
  methods: {
    getReview: function() {
      axios
        .get(`${SERVER_URL}/review/store/` + `${this.storeId}`)
        .then((response) => {
          this.reviews2 = response.data;
          this.reviews = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  }
}
</script>

<style>

</style>