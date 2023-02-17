<template>
  <div :class="[multipage === true ? 'multi-page':'single-page', 'not-menu-page', 'home-page']" style="background-color: #f0f2f5; border: none">
    <a-row v-if="newsList.length > 0" style="width: 80%;margin: 0 auto;margin-bottom: 15px">
      <a-col :span="22">
        <a-alert
          banner
          :message="newsContent"
          type="info"
        />
      </a-col>
      <a-col :span="2">
        <a-button type="primary" style="margin-top: 2px;margin-left: 10px" @click="newsNext">下一页</a-button>
      </a-col>
    </a-row>
    <a-row :gutter="8" class="head-info">
      <a-card class="head-info-card" style="width: 80%;margin: 0 auto">
        <a-col :span="12">
          <div class="head-info-avatar">
            <img alt="头像" :src="avatar">
          </div>
          <div class="head-info-count">
            <div class="head-info-welcome">
              {{welcomeMessage}}
            </div>
            <div class="head-info-desc">
              <p>{{user.roleName ? user.roleName : '暂无角色'}}</p>
            </div>
            <div class="head-info-time">上次登录时间：{{user.lastLoginTime ? user.lastLoginTime : '第一次访问系统'}}</div>
          </div>
        </a-col>
        <a-col :span="12">
          <div>
            <a-row class="more-info">
              <a-col :span="4"></a-col>
              <a-col :span="4"></a-col>
              <a-col :span="4"></a-col>
              <a-col :span="4"></a-col>
              <a-col :span="4"></a-col>
              <a-col :span="4">
              </a-col>
            </a-row>
          </div>
        </a-col>
      </a-card>
    </a-row>
    <a-row :gutter="8" class="count-info">
      <a-card class="head-info-card" style="width: 80%;margin: 0 auto"></a-card>
    </a-row>
  </div>
</template>
<script>
import HeadInfo from '@/views/common/HeadInfo'
import {mapState} from 'vuex'
import moment from 'moment'
moment.locale('zh-cn')
function getBase64 (file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onload = () => resolve(reader.result)
    reader.onerror = error => reject(error)
  })
}

export default {
  name: 'HomePage',
  components: {HeadInfo},
  data () {
    return {
      newsPage: 0,
      newsContent: '',
      newsList: [],
      todayIp: '',
      todayVisitCount: '',
      totalVisitCount: '',
      userRole: '',
      userDept: '',
      lastLoginTime: '',
      welcomeMessage: ''
    }
  },
  watch: {
  },
  computed: {
    ...mapState({
      multipage: state => state.setting.multipage,
      user: state => state.account.user
    }),
    avatar () {
      return `static/avatar/${this.user.avatar}`
    }
  },
  methods: {
    async handlePreview (file) {
      if (!file.url && !file.preview) {
        file.preview = await getBase64(file.originFileObj)
      }
      this.previewImage = file.url || file.preview
      this.previewVisible = true
    },
    handleCancel () {
      this.previewVisible = false
    },
    welcome () {
      const date = new Date()
      const hour = date.getHours()
      let time = hour < 6 ? '早上好' : (hour <= 11 ? '上午好' : (hour <= 13 ? '中午好' : (hour <= 18 ? '下午好' : '晚上好')))
      return `${time}，${this.user.username}`
    },
    timeFormat (time) {
      var nowTime = new Date()
      var day = nowTime.getDate()
      var hours = parseInt(nowTime.getHours())
      var minutes = nowTime.getMinutes()
      // 开始分解付入的时间
      var timeday = time.substring(8, 10)
      var timehours = parseInt(time.substring(11, 13))
      var timeminutes = time.substring(14, 16)
      // eslint-disable-next-line camelcase
      var d_day = Math.abs(day - timeday)
      // eslint-disable-next-line camelcase
      var d_hours = hours - timehours
      // eslint-disable-next-line camelcase
      var d_minutes = Math.abs(minutes - timeminutes)
      // eslint-disable-next-line camelcase
      if (d_day <= 1) {
        // eslint-disable-next-line camelcase
        switch (d_day) {
          case 0:
            // eslint-disable-next-line camelcase
            if (d_hours === 0 && d_minutes > 0) {
              // eslint-disable-next-line camelcase
              return d_minutes + '分钟前'
              // eslint-disable-next-line camelcase
            } else if (d_hours === 0 && d_minutes === 0) {
              return '1分钟前'
            } else {
              // eslint-disable-next-line camelcase
              return Math.abs(d_hours) + '小时前'
            }
            // eslint-disable-next-line no-unreachable
            break
          case 1:
            // eslint-disable-next-line camelcase
            if (d_hours < 0) {
              // eslint-disable-next-line camelcase
              return (24 + d_hours) + '小时前'
            } else {
              // eslint-disable-next-line camelcase
              return d_day + '天前'
            }
            // eslint-disable-next-line no-unreachable
            break
        }
        // eslint-disable-next-line camelcase
      } else if (d_day > 1 && d_day < 10) {
        // eslint-disable-next-line camelcase
        return d_day + '天前'
      } else {
        return time
      }
    },
    getNewList () {
      this.$get(`/cos/bulletin-info/list`).then((r) => {
        this.newsList = r.data.data
        if (this.newsList.length !== 0) {
          this.newsContent = `《${this.newsList[0].title}》 ${this.newsList[0].content}`
        }
      })
    }
  },
  mounted () {
    this.welcomeMessage = this.welcome()
    this.$get(`index/${this.user.username}`).then((r) => {
      let data = r.data.data
      this.todayIp = data.todayIp
      this.todayVisitCount = data.todayVisitCount
      this.totalVisitCount = data.totalVisitCount
      let sevenVisitCount = []
      let dateArr = []
      for (let i = 6; i >= 0; i--) {
        let time = moment().subtract(i, 'days').format('MM-DD')
        let contain = false
        for (let o of data.lastSevenVisitCount) {
          if (o.days === time) {
            contain = true
            sevenVisitCount.push(o.count)
          }
        }
        if (!contain) {
          sevenVisitCount.push(0)
        }
        dateArr.push(time)
      }
      let sevenUserVistCount = []
      for (let i = 6; i >= 0; i--) {
        let time = moment().subtract(i, 'days').format('MM-DD')
        let contain = false
        for (let o of data.lastSevenUserVisitCount) {
          if (o.days === time) {
            contain = true
            sevenUserVistCount.push(o.count)
          }
        }
        if (!contain) {
          sevenUserVistCount.push(0)
        }
      }
    }).catch((r) => {
      console.error(r)
      this.$message.error('获取首页信息失败')
    })
  }
}
</script>
<style lang="less">
  .home-page {
    .head-info {
      margin-bottom: .5rem;
      .head-info-card {
        padding: .5rem;
        border-color: #f1f1f1;
        .head-info-avatar {
          display: inline-block;
          float: left;
          margin-right: 1rem;
          img {
            width: 5rem;
            border-radius: 2px;
          }
        }
        .head-info-count {
          display: inline-block;
          float: left;
          .head-info-welcome {
            font-size: 1.05rem;
            margin-bottom: .1rem;
          }
          .head-info-desc {
            color: rgba(0, 0, 0, 0.45);
            font-size: .8rem;
            padding: .2rem 0;
            p {
              margin-bottom: 0;
            }
          }
          .head-info-time {
            color: rgba(0, 0, 0, 0.45);
            font-size: .8rem;
            padding: .2rem 0;
          }
        }
      }
    }
    .count-info {
      .visit-count-wrapper {
        padding-left: 0 !important;
        .visit-count {
          padding: .5rem;
          border-color: #f1f1f1;
          .ant-card-body {
            padding: .5rem 1rem !important;
          }
        }
      }
      .project-wrapper {
        padding-right: 0 !important;
        .project-card {
          border: none !important;
          .ant-card-head {
            border-left: 1px solid #f1f1f1 !important;
            border-top: 1px solid #f1f1f1 !important;
            border-right: 1px solid #f1f1f1 !important;
          }
          .ant-card-body {
            padding: 0 !important;
            table {
              width: 100%;
              td {
                width: 50%;
                border: 1px solid #f1f1f1;
                padding: .6rem;
                .project-avatar-wrapper {
                  display:inline-block;
                  float:left;
                  margin-right:.7rem;
                  .project-avatar {
                    color: #42b983;
                    background-color: #d6f8b8;
                  }
                }
              }
            }
          }
          .project-detail {
            display:inline-block;
            float:left;
            text-align:left;
            width: 78%;
            .project-name {
              font-size:.9rem;
              margin-top:-2px;
              font-weight:600;
            }
            .project-desc {
              color:rgba(0, 0, 0, 0.45);
              p {
                margin-bottom:0;
                font-size:.6rem;
                white-space:normal;
              }
            }
          }
        }
      }
    }
  }
</style>
