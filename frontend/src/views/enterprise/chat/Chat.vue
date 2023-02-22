<template>
  <a-row style="width: 100%;">
    <a-col :span="4">
      <a-menu mode="inline" style="width: 100%;margin-top: 30px" v-if="contactList.length !== 0">
        <a-menu-item :key="index" style="height: 80px;margin: 0 auto;text-align: center;line-height: 80px" v-for="(item, index) in contactList" @click="onChange(item)">
          <a-avatar
            :size="46"
            icon="user"
            :src="'http://127.0.0.1:9527/imagesWeb/' + item.images"
          />
          <span style="margin-left: 35px;font-size: 15px">
            {{ item.name }}
          </span>
          <span style="font-size: 13px">【{{ item.position }}】</span>
        </a-menu-item>
      </a-menu>
      <div style="text-align: center;margin-top: 50px" v-else>
        <a-icon type="meh" theme="twoTone" style="font-size: 50px;"/>
        <p style="font-size: 20px;margin-top: 30px">暂无联系人</p>
      </div>
    </a-col>
    <a-col :span="20">
      <a-list
        class="comment-list"
        item-layout="horizontal"
        :data-source="chatList"
      >
        <a-list-item slot="renderItem" slot-scope="item, index">
          <a-comment :author="item.name" :avatar="'http://127.0.0.1:9527/imagesWeb/' + item.images">
            <p slot="content">
              {{ item.content }}
            </p>
            <a-tooltip slot="datetime" :title="item.createDate">
              <span>{{ item.createDate }}</span>
            </a-tooltip>
          </a-comment>
        </a-list-item>
      </a-list>
    </a-col>
  </a-row>
</template>

<script>
import {mapState} from 'vuex'
export default {
  name: 'Chat',
  computed: {
    ...mapState({
      multipage: state => state.setting.multipage,
      user: state => state.account.user
    })
  },
  data () {
    return {
      chatList: [],
      contactList: []
    }
  },
  mounted () {
    this.selectContactPerson()
  },
  methods: {
    selectContactPerson () {
      this.$get(`/cos/chat-info/contact/person`, {
        userCode: this.user.userCode,
        flag: 2
      }).then((r) => {
        this.contactList = r.data.data
      })
    },
    onChange (item) {
      this.$get(`/cos/chat-info/record`, {
        expertCode: item.expertCode,
        enterpriseCode: item.enterpriseCode
      }).then((r) => {
        this.chatList = r.data.data
      })
    }
  }
}
</script>
<style scoped>

</style>
