<template>
  <a-row style="width: 100%;">
    <a-col :span="4">
      <a-menu mode="inline" style="width: 100%;margin-top: 30px">
        <a-menu-item key="mail" style="height: 80px;margin: 0 auto">
          <a-avatar
            :size="46"
            icon="user"
            :src="'http://127.0.0.1:9527/imagesWeb/SA1676716298671.jpg'"
          />
          <span style="margin-left: 15px">孙笑川</span>
        </a-menu-item>
        <a-menu-item key="mail1" style="height: 80px">
          <a-avatar
            :size="46"
            icon="user"
            :src="'http://127.0.0.1:9527/imagesWeb/SA1676716298671.jpg'"
          />
          <span style="margin-left: 15px">孙笑川</span>
        </a-menu-item>
      </a-menu>
    </a-col>
  </a-row>
</template>

<script>
export default {
  name: 'Chat',
  data () {
    return {
    }
  },
  methods: {
  }
}
</script>
<style scoped>

</style>
