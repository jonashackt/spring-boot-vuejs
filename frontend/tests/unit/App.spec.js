import { shallowMount } from '@vue/test-utils';
import App from '@/App';

// see https://vue-test-utils.vuejs.org/guides/using-with-vue-router.html#testing-components-that-use-router-link-or-router-view
describe('App component should', () => {
  it('render without crashing', () => {
    const wrapper = shallowMount(App, {
      stubs: ['router-link', 'router-view']
    });
    expect(wrapper.find('hello')).toBeDefined();
  });
});
