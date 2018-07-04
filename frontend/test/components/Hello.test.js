import { shallowMount } from '@vue/test-utils';
import Hello from '@/components/Hello'

describe('Hello.vue', () => {
  it('should render correct hello message', () => {
    // Given
    const hellowrapped = shallowMount(Hello, {
      // see https://stackoverflow.com/a/37940045/4964553
      propsData: { hellomsg: 'Welcome to your Jest powered Vue.js App' },
      // see https://vue-test-utils.vuejs.org/guides/using-with-vue-router.html#testing-components-that-use-router-link-or-router-view
      stubs: ['router-link', 'router-view']
    });

    // When
    const contentH1 = hellowrapped.find('h1');

    // Then
    expect(contentH1.text()).toEqual('Welcome to your Jest powered Vue.js App');
  })
})
