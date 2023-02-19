<template>
  <a-card style="width: 63%;margin: 0 auto;padding-top: 30px">
    <i style="font-size: 23px;margin: 30px;">专家列表</i>
    <div style="margin: 30px">
      <a-list
        class="demo-loadmore-list"
        :loading="loading"
        item-layout="horizontal"
        :data-source="expertList"
      >
        <a-list-item slot="renderItem" slot-scope="item, index">
          <a slot="actions">edit</a>
          <a slot="actions">more</a>
          <a-list-item-meta
            description="Ant Design, a design language for background applications, is refined by Ant UED Team"
          >
            <a slot="title" href="https://www.antdv.com/">{{ item.name }}</a>
            <a-avatar
              slot="avatar"
              src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
            />
          </a-list-item-meta>
          <div>content</div>
        </a-list-item>
      </a-list>
    </div>
  </a-card>
</template>

<script>
export default {
  name: 'Expert',
  data () {
    return {
      loading: false,
      data: [],
      expertList: []
    }
  },
  mounted () {
    this.getEnterpriseList()
  },
  methods: {
    getEnterpriseList (key) {
      if (key === undefined || key === null) {
        key = ''
      }
      this.$get(`/cos/expert-info/list/${key}`).then((r) => {
        this.expertList = r.data.data
      })
    }
  }
}
</script>

<style scoped>

</style>
