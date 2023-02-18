<template>
  <a-row style="width: 80%;margin: 0 auto">
    <a-col :span="18">
      <a-row>
        <a-col :span="24">
          <a-col style="margin-bottom: 15px"><i style="font-size: 23px;color: #000c17">专家</i></a-col>
          <a-col>
            <a-row :gutter="15">
              <a-col>
                <a-card hoverable style="width: 300px">
                  <template slot="actions" class="ant-card-actions">
                    <a-icon key="setting" type="setting" />
                    <a-icon key="edit" type="edit" />
                    <a-icon key="ellipsis" type="ellipsis" />
                  </template>
                  <a-card-meta title="Card title" description="This is the description">
                    <a-avatar
                      slot="avatar"
                      src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
                    />
                  </a-card-meta>
                </a-card>
              </a-col>
            </a-row>
          </a-col>
        </a-col>
        <a-col :span="24" style="margin-top: 20px">
          <a-col style="margin-bottom: 15px"><i style="font-size: 23px;color: #000c17">企业</i></a-col>
          <a-col>
            <a-row :gutter="15">
              <a-col>
                <a-card hoverable style="width: 300px">
                  <template slot="actions" class="ant-card-actions">
                    <a-icon key="setting" type="setting" />
                    <a-icon key="edit" type="edit" />
                    <a-icon key="ellipsis" type="ellipsis" />
                  </template>
                  <a-card-meta title="Card title" description="This is the description">
                    <a-avatar
                      slot="avatar"
                      src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
                    />
                  </a-card-meta>
                </a-card>
              </a-col>
            </a-row>
          </a-col>
        </a-col>
      </a-row>
    </a-col>
    <a-col :span="6">
      <a-card hoverable :bordered="false" title="公告信息" style="height: 750px;overflow: auto">
        <div style="padding: 0 22px">
          <a-list item-layout="vertical" :pagination="false" :data-source="bulletinList">
            <a-list-item slot="renderItem" key="item.title" slot-scope="item, index">
              <template slot="actions">
              <span key="message">
                <a-icon type="message" style="margin-right: 8px" />
                {{ item.date }}
              </span>
              </template>
              <a-list-item-meta :description="item.content" style="font-size: 14px">
                <a slot="title">{{ item.title }}</a>
              </a-list-item-meta>
            </a-list-item>
          </a-list>
        </div>
      </a-card>
    </a-col>
  </a-row>
</template>

<script>
export default {
  name: 'home',
  data () {
    return {
      bulletinList: [],
      expertList: [],
      enterpriseList: []
    }
  },
  mounted () {
    this.getBulletinData()
    this.getExpertData()
    this.getEnterpriseData()
  },
  methods: {
    getBulletinData () {
      this.$get(`/cos/bulletin-info/list`).then((r) => {
        this.bulletinList = r.data.data
      })
    },
    getExpertData (key) {
      this.$get(`/cos/expert-info/list/{key}`).then((r) => {
        this.expertList = r.data.data
      })
    },
    getEnterpriseData (key) {
      this.$get(`/cos/enterprise-info/list/{key}`).then((r) => {
        this.enterpriseList = r.data.data
      })
    }
  }
}
</script>

<style scoped>

</style>
