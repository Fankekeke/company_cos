<template>
  <a-modal v-model="show" title="导入专家信息" @cancel="onClose" :width="500" :footer="null">
    <a-upload-dragger
      v-if="expertVisiable"
      name="file"
      :multiple="true"
      accept=".xls, .xlsx"
      action="http://127.0.0.1:9527/cos/expert-info/import"
      @change="handleChange"
    >
      <p class="ant-upload-drag-icon">
        <a-icon type="inbox" />
      </p>
      <p class="ant-upload-text">
        Click or drag file to this area to upload
      </p>
      <p class="ant-upload-hint">
        Support for a single or bulk upload
      </p>
    </a-upload-dragger>
    <a-button-group style="margin-top: 20px">
      <a-button type="primary" icon="cloud-download" />
    </a-button-group>
  </a-modal>
</template>

<script>
import {mapState} from 'vuex'

export default {
  name: 'ExpertAdd',
  props: {
    expertVisiable: {
      default: false
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.expertVisiable
      },
      set: function () {
      }
    }
  },
  data () {
    return {
      form: this.$form.createForm(this),
      loading: false,
      fileList: [],
      previewVisible: false,
      previewImage: ''
    }
  },
  methods: {
    handleChange ({file}) {
      console.log(file)
      console.log(file.response.code)
      if (file.response.code === 500 && file.status === 'done') {
        this.$message.error(file.response.msg)
      }
    },
    onClose () {
      this.$emit('close')
    }
  }
}
</script>

<style scoped>

</style>
