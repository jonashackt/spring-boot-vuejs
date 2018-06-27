import { shallowMount } from '@vue/test-utils';
import Hello from '@/components/Hello'

describe('Hello.vue', () => {
  it('should render correct hello message', () => {
    const wrapper = shallowMount(Hello, { propsData: { hellomsg: 'Welcome to your Jest powered Vue.js App' } });
    const contentH1 = wrapper.find('h1');
    expect(contentH1.text()).toEqual('Welcome to your Jest powered Vue.js App');
  })
})
