import { shallowMount } from '@vue/test-utils';
import Hello from '@/components/Hello'

describe('Hello.vue', () => {
  it('should render correct hello message', () => {
    // Given
    const hellowrapped = shallowMount(Hello, { propsData: { hellomsg: 'Welcome to your Jest powered Vue.js App' } });
    // When
    const contentH1 = hellowrapped.find('h1');
    // Then
    expect(contentH1.text()).toEqual('Welcome to your Jest powered Vue.js App');
  })
})
