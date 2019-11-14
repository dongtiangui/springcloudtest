<template>
  <div>
      <b-tabs>
        <!-- Render Tabs, supply a unique `key` to each tab -->
        <b-tab v-for="i in tabs" :key="`dyn-tab-${i}`" :title="`Tab ${i}`">
          Tab Contents {{ i }}
          <b-button size="sm" variant="danger" class="float-right" @click="() => closeTab(i)">
            Close tab
          </b-button>
        </b-tab>

        <template slot="tabs">
          <b-nav-item @click.prevent="newTab" href="#"><b>+</b></b-nav-item>
        </template>

        <!-- Render this if no tabs -->
        <div slot="empty" class="text-center text-muted">
          There are no open tabs<br>
          Open a new tab using the <b>+</b> button above.
        </div>
      </b-tabs>
  </div>
</template>
<script>
  export default {
    name: 'maintab',
    data() {
      return {
        tabs: [],
        tabCounter: 0
      }
    },
    methods: {
      closeTab(x) {
        for (let i = 0; i < this.tabs.length; i++) {
          if (this.tabs[i] === x) {
            this.tabs.splice(i, 1)
          }
        }
      },
      newTab() {
        this.tabs.push(this.tabCounter++)
      }
    }
  }
</script>

<style scoped>
  .b-tabs{
    height: 200px !important;
  }
</style>
