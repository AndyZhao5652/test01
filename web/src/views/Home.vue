<template>
<a-layout>
  <a-layout-sider width="200" style="background: #fff">
    <a-menu
        v-model:selectedKeys="selectedKeys2"
        v-model:openKeys="openKeys"
        mode="inline"
        style="height: 100%"
    >
      <a-sub-menu key="sub1">
        <template #title>
            <span>
              <user-outlined/>
              subnav 1
            </span>
        </template>
        <a-menu-item key="1">option1</a-menu-item>
        <a-menu-item key="2">option2</a-menu-item>
        <a-menu-item key="3">option3</a-menu-item>
        <a-menu-item key="4">option4</a-menu-item>
      </a-sub-menu>
      <a-sub-menu key="sub2">
        <template #title>
            <span>
              <laptop-outlined/>
              subnav 2
            </span>
        </template>
        <a-menu-item key="5">option5</a-menu-item>
        <a-menu-item key="6">option6</a-menu-item>
        <a-menu-item key="7">option7</a-menu-item>
        <a-menu-item key="8">option8</a-menu-item>
      </a-sub-menu>
      <a-sub-menu key="sub3">
        <template #title>
            <span>
              <notification-outlined/>
              subnav 3
            </span>
        </template>
        <a-menu-item key="9">option9</a-menu-item>
        <a-menu-item key="10">option10</a-menu-item>
        <a-menu-item key="11">option11</a-menu-item>
        <a-menu-item key="12">option12</a-menu-item>
      </a-sub-menu>
    </a-menu>
  </a-layout-sider>
  <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
    <a-list item-layout="vertical" size="large" :grid="{ gutter: 20, column: 3}" :data-source="ebooks">
      <template #renderItem="{ item }">
        <a-list-item key="item.name">
          <template #actions>
      <span v-for="{ type, text } in actions" :key="type">
        <component :is="type" style="margin-right: 8px"/>
        {{ text }}
      </span>
          </template>
          <a-list-item-meta :description="item.description">
            <template #title>
              <a :href="item.href">{{ item.name }}</a>
            </template>
            <template #avatar>
              <a-avatar :src="item.cover"/>
            </template>
          </a-list-item-meta>
        </a-list-item>
      </template>
    </a-list>
  </a-layout-content>
</a-layout>
</template>
<script lang="ts">

import { defineComponent, onMounted, ref, toRef } from 'vue';
import axios from 'axios';

const listData: any = [];
for (let i = 0; i < 23; i++) {
  listData.push({
    href: 'https://www.antdv.com/',
    title: `ant design vue part ${i}`,
    avatar: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',
    description:
        'Ant Design, a design language for background applications, is refined by Ant UED Team.',
    content:
        'We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
  });
}
export default defineComponent({
  name: 'Home',
  setup(){
    const ebooks = ref();

    onMounted(() => {
      axios.get("http://localhost:8880/ebook/list").then((response) =>{
        const data = response.data;
        ebooks.value = data.content;
      });
    });

    return{
      ebooks,
      listData,
      pagination: {
        onchange: (page: any) => {
          console.log(page);
        },
        pageSize: 3,
      },
      actions: [
        { type: 'StarOutlined', text: '111'},
        { type: 'LinkOutlined', text: '222'},
        { type: 'MessageOutlined', text: '333'},
      ],
    }
  }
});
</script>
<style>
#components-layout-demo-top-side .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}

.ant-avatar {
  width: 50px;
  height: 50px;
  line-height: 50px;
  border-radius: 8%;
  margin: 5px 0;
}

</style>