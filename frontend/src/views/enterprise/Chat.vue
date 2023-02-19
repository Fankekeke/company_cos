<template>
  <div class='main'>
    <div class='input-name' v-show='inputDisplay'>
      <a-input
        placeholder='输入昵称后按回车进入聊天室'
        v-model='nickname'
        @keyup.enter='setName'
      />
    </div>
    <div v-show='messageDisplay'>
      <a-tabs
        default-active-key='1'
        tab-position='left'
        type='line'
        @change='callback'
      >
        <a-tab-pane key='1'>
          <span slot='tab'>
            <a-icon type='message' />
          </span>
          <a-layout id='sider'>
            <a-layout-sider :trigger='null' collapsible>
              <a-row class='input-row'>
                <a-col :span='20'>
                  <a-input-search
                    class='search-input'
                    style='width: 95%'
                    placeholder='搜索'
                    @search='onSearch'
                  />
                </a-col>
                <a-col :span='4'>
                  <a-button
                    icon='plus'
                    @click='handlerCreateChatroom'
                  />
                </a-col>
              </a-row>
              <a-menu
                theme='light'
                mode='inline'
                :default-selected-keys='[]'
                @click='handleClick'
              >
                <template v-for='item in chatroomList'>
                  <a-menu-item :key='item.id'>
                    <a-icon type='user-add' />
                    <span>{{item.name}}</span>
                  </a-menu-item>
                </template>
              </a-menu>
            </a-layout-sider>
            <a-layout>
              <!--  <a-layout-header style='background: #fff padding: 0'>
          </a-layout-header> -->
              <a-layout-content>
                <div class='room-len'>当前房间在线人数：{{ roomLen }}</div>
                <div class='message-box' id='scroll-box'>
                  <div class='scroll_content'>
                    <ul>
                      <li v-for='(item, index) in records' :key='index'>
                        <p class='time'>
                          <span>{{ item.time }}</span>
                        </p>
                        <div :class='{ self: item.self }' class>
                          <div class='nickname'>{{ item.nickname }}</div>
                          <img
                            src=''
                            class='avatar'
                            width='30'
                            height='30'
                            @click='handlerAddFrind(item.sender)'
                          />
                          <div class='text'>
                            <span v-html='item.content' />
                          </div>
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
                <div class='input-box'>
                  <div class='chat_edit'>
                    <div class='chat_edit_input'>
                      <div>
                        <textarea
                          v-model='content'
                          :rows='6'
                          placeholder='请输入内容'
                          @keyup.enter='speak'
                        />
                      </div>
                    </div>
                    <div class='chat_edit_footer'>
                      按Enter发送，Ctrl+Enter换行
                    </div>
                  </div>
                </div>
              </a-layout-content>
            </a-layout>
          </a-layout>
        </a-tab-pane>
        <a-tab-pane key='2' force-render>
          <span slot='tab'>
            <a-icon type='contacts' />
          </span>
          <a-layout id='sider'>
            <a-layout-sider :trigger='null' collapsible>
              <a-menu
                theme='light'
                mode='inline'
                :default-selected-keys='[]'
                @click='handleClickList'
              >
                <a-menu-item key='0'>
                  <a-icon type='user-add' />
                  <span>新的好友</span>
                </a-menu-item>
                <a-menu-item key='1'>
                  <a-icon type='user' />
                  <span>我的好友</span>
                </a-menu-item>
                <a-menu-item key='2'>
                  <a-icon type='team' />
                  <span>我的群聊</span>
                </a-menu-item>
              </a-menu>
            </a-layout-sider>
            <a-layout>
              <a-layout-content>
                <a-list bordered :data-source='friendshipData'>
                  <a-list-item slot='renderItem' slot-scope='item, index'>
                    <a-list-item-meta
                      v-if='item.status == 0'
                      :description='item.message'
                    >
                      <a slot='title'>{{ item.applicant }} </a>
                      <a-avatar
                        slot='avatar'
                        src='/chat-room/_nuxt/assets/avatar.png'
                      />
                    </a-list-item-meta>
                    <a-list-item-meta v-else>
                      <a slot='title'>{{ item.applicant }} </a>
                      <a-avatar
                        slot='avatar'
                        src=''
                      />
                    </a-list-item-meta>
                    <div v-if='item.status == 0'>
                      <a-button type='primary' @click='aggree(item)'>
                        同意
                      </a-button>
                    </div>
                    <div v-else>
                      <a-button type='primary' @click='unfriend(item)'>
                        删除好友
                      </a-button>
                    </div>
                  </a-list-item>
                  <div slot='header'>{{ headerContent }}</div>
                </a-list>
              </a-layout-content>
            </a-layout>
          </a-layout>
        </a-tab-pane>
      </a-tabs>

      <a-modal
        v-model='modalVisible'
        title='用户'
        centered
        :footer='null'
        @ok=' () => (modalVisible = false)'
      >
        <p>个性签名</p>
        <p>
          <a-input placeholder='你好，我是xxx' v-model='newFriendMessage' />
        </p>
        <p><a-button type='primary' @click='addFrind'> 加好友 </a-button></p>
      </a-modal>
      <a-modal
        v-model='friendModalVisible'
        title='创建群聊'
        centered
        cancelText='取消'
        okText='确定'
        @ok='createChatroom'
      >
        <a-row class='input-row'>
          <a-col :span='24'>
            <a-checkbox-group @change='onChange'>
              <a-list :data-source='friendshipData'>
                <a-list-item slot='renderItem' slot-scope='item, index' >
                  <a-checkbox :value='item.applicant'>
                    <a-list-item-meta>
                      <span slot='title'>{{ item.applicant }} </span>
                      <a-avatar
                        slot='avatar'
                        src=''
                      />
                    </a-list-item-meta>
                  </a-checkbox>
                </a-list-item>
              </a-list>
            </a-checkbox-group>
          </a-col>
        </a-row>
      </a-modal>
    </div>
  </div>
</template>

<script>

export default {
  data  () {
    return {
      content: '',
      inputDisplay: true,
      messageDisplay: false,
      nickname: '',
      sender: '',
      records: '',
      loading: false,
      more_text_display: true,
      avatar: '',
      roomLen: 0,
      roomId: undefined,
      modalVisible: false,
      newFriend: undefined,
      newFriendMessage: '',
      friendshipData: [],
      headerContent: '',
      friendModalVisible: false,
      checkedFriends: [],
      chatroomList: []
    }
  },
  watch: {
    roomId: function (e) {}
  },
  methods: {
    setName () {
      this.inputDisplay = false
      this.messageDisplay = true
      let sender = window.btoa(window.encodeURIComponent(this.nickname))
      this.sender = sender
      this.$socket.emit('enterRoom', { id: sender })
    },
    speak () {
      let isSeensitive = this.$myInjectedFunction(this.content)
      if (isSeensitive) {
        this.$message.warning('输入内容包含敏感词')
        return false
      }
      this.$socket.emit('sendToRoom', {
        uid: this.sender,
        content: window.btoa(window.encodeURIComponent(this.content)),
        roomId: !this.roomId ? 1 : this.roomId
      })
      this.content = ''
    },
    onSearch: function () {},
    handleClick: function (e) {
      this.roomId = e.key.toString()
      this.$socket.emit('enterRoom', { uid: this.sender, roomId: this.roomId })
    },
    handlerAddFrind: function (e) {
      this.modalVisible = true
      this.newFriend = e
    },
    addFrind: function () {
      this.$socket.emit('addFriend', {
        applicant: this.sender,
        respondent: this.newFriend,
        message: this.newFriendMessage
      })
    },
    callback: function () {},
    async handleClickList (e) {
      if (e.key === 0) {
        this.headerContent = '申请列表'
      } else {
        this.headerContent = '好友列表'
      }
      const list = await this.$axios.$get(
        'http://localhost:8199/client/friendship?respondent=' +
        this.sender +
        '&status=' +
        e.key
      )
      this.friendshipData = []
      if (list.data) {
        this.friendshipData = list.data
      }
    },
    async aggree (row) {
      let response = await this.$axios.$get(
        'http://localhost:8199/client/aggree?id=' +
        row.id +
        '&respondent=' +
        this.sender
      )
      if (response.code === 200) {
        this.$message.success(response.message)
        let index = this.friendshipData.indexOf(row)
        this.friendshipData.splice(index, 1)
      } else {
        this.$message.error(response.message)
      }
    },
    async unfriend (row) {
      const response = await this.$axios.$get(
        'http://localhost:8199/client/unfriend?id=' +
        row.id +
        '&respondent=' +
        this.sender
      )
      if (response.code === 200) {
        this.$message.success(response.message)
        let index = this.friendshipData.indexOf(row)
        this.friendshipData.splice(index, 1)
      } else {
        this.$message.error(response.message)
      }
    },
    async handlerCreateChatroom () {
      this.friendModalVisible = true
      let list = await this.$axios.$get(
        'http://localhost:8199/client/friendship?respondent=' +
        this.sender +
        '&status=1'
      )
      this.friendshipData = []
      if (list.data) {
        this.friendshipData = list.data
      }
    },
    async createChatroom () {
      let res = await this.$axios.$post(
        'http://localhost:8199/client/createChatroom',
        {
          uid: this.sender,
          checkedFriends: this.checkedFriends
        }
      )
      if (res.code === 200) {
        this.$message.success('操作成功')
      } else {
        this.$message.warning('操作失败')
      }
      this.friendModalVisible = false
    },
    async getChatroomList (e) {
      const list = await this.$axios.$get(
        'http://localhost:8199/client/getChatroomList?uid=' +
        this.sender
      )
      this.chatroomList = []
      if (list.data) {
        this.chatroomList = list.data
      }
    },
    onChange (checkedValues) {
      this.checkedFriends = checkedValues
    }
  },
  mounted () {
    let sender = Cookies.get('sender')
    console.log('进入聊天室', sender)
    if (sender) {
      this.inputDisplay = false
      this.messageDisplay = true
      this.sender = sender
      this.$socket.emit('enterRoom', { uid: sender, roomId: 1 })
    }
    this.getChatroomList()
  },
  sockets: {
    broadcast: function (msg) {
      let item = {}
      if (msg.Sender === this.sender) {
        item.self = true
      } else {
        item.self = false
      }
      item.id = msg.Id
      item.content = msg.Content
      item.time = msg.Time
      item.sender = msg.Sender
      item.nickname = window.decodeURIComponent(window.atob(item.sender))
      this.records.push(item)
      setTimeout(() => {
        let oContent = document.getElementById('scroll-box')
        oContent.scrollTop = oContent.scrollHeight
      }, 0)
    },
    initRoomData: function (msg) {
      this.records = []
      if (msg.length > 0) {
        msg.forEach((element) => {
          if (element.sender === this.sender) {
            element.self = true
          } else {
            element.self = false
          }
          element.nickname = window.decodeURIComponent(
            window.atob(element.sender)
          )
          element.content = window.decodeURIComponent(element.content)
        })
      }
      this.records = msg
      setTimeout(() => {
        let oContent = document.getElementById('scroll-box')
        oContent.scrollTop = oContent.scrollHeight
      }, 0)
    },
    roomLen: function (msg) {
      this.roomLen = msg
    }
  }
}
</script>

<style lang='scss'>
.container {
  margin: 0 auto;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center
}
.main {
  margin-top: 50px;
  min-height: 800px;
  background: #f5f5f5;
  border: 1px solid #f5f5f5;
  box-shadow: 1px 1px 8px #b7b4b4;
}
.message-box {
  height: 592px;
  overflow: scroll;
  &::-webkit-scrollbar {
    width: 6px; /*高宽分别对应横竖滚动条的尺寸*/
    height: 1px
  }
  &::-webkit-scrollbar-thumb {
    border-radius: 6px;
    // -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2)
    background: rgba(144, 147, 153, 0.5)
  }
  &::-webkit-scrollbar-track {
    // -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2)
    border-radius: 5px;
    background: transparent;
  }
}
.title {
  font-family: 'Quicksand', 'Source Sans Pro', -apple-system, BlinkMacSystemFont,
  'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
  display: block;
  font-weight: 300;
  font-size: 100px;
  color: #35495e;
  letter-spacing: 1px;
}
.subtitle {
  font-weight: 300;
  font-size: 42px;
  color: #526488;
  word-spacing: 5px;
  padding-bottom: 15px;
}
.links {
  padding-top: 15px
}
.chat_edit_items {
  height: 40px;
  padding: 0 20px
}
.chat_edit_input {
  height: 120px;
  overflow: hidden
}
.chat_edit_footer {
  height: 30px;
  padding: 0 20px;
  text-align: right;
  color: #bcbcbc
}
textarea {
  padding: 10px;
  height: 60%;
  width: 100%;
  border: none;
  outline: none;
  font-family: 'Micrsofot Yahei';
  resize: none;
  background: #f5f5f5
}
.input-box {
  width: 100%;
  margin-top: 10px;
  border-top: 1px solid #dcdfe6;
}
.input-name {
  margin-top: 200px;
  max-width: 300px;
  margin: 200px auto 0 auto;
}
.scroll_content {
  padding: 10px 15px;
  ul {
    padding: 20px 0 0 0;
    li {
      margin-bottom: 15px;
      list-style-type: none
    }
  }
  .more {
    text-align: center;
    color: #409eff;
    font-size: 12px;
  }
  .time {
    margin: 7px 0;
    text-align: center
    > span {
      display: inline-block;
      padding: 0 18px;
      font-size: 12px;
      color: #fff;
      border-radius: 2px;
      background-color: #dcdcdc
    }
  }
  .avatar {
    float: left;
    margin: 0 10px 0 0;
    border-radius: 3px
  }
  .text {
    display: inline-block;
    position: relative;
    padding: 0 10px;
    max-width: calc(100% - 40px);
    min-height: 30px;
    line-height: 2.5;
    font-size: 14px;
    text-align: left;
    word-break: break-all;
    background-color: #fff;
    border-radius: 4px;
    &:before {
      content: '';
      position: absolute;
      top: 9px;
      right: 100%;
      border: 6px solid transparent;
      border-right-color: #fff
    }
  }
  .nickname {
    padding: 5px 40px 5px 40px;
    font-size: 12px;
    color: #777;
  }
  .self {
    text-align: right;
    .nickanme {
      padding-right: 40px
    }
    .avatar {
      float: right;
      margin: 0 0 0 10px
    }
    .text {
      background-color: #b2e281;
      &:before {
        right: inherit;
        left: 100%;
        border-right-color: transparent;
        border-left-color: #b2e281
      }
    }
  }
}
.avatar:hover {
  cursor: pointer
}
.room-len {
  padding: 12px;
  border-bottom: 1px solid #ccc
}
#sider {
  min-height: 800px
}
.search-input > .ant-input {
  background: #f5f5f5;
  border: none;
  border-radius: 0
}
.ant-layout {
  background: none !important
}
.ant-tabs .ant-tabs-left-content {
  padding-left: 0 !important
}
.ant-menu-inline .ant-menu-item {
  margin-top: 0 !important
}
.ant-layout-sider {
  background: #fff !important
}
.input-row {
  padding: 4px
}
.ant-checkbox-wrapper> span{
  display: inline-block;
  float: left
}
</style>
