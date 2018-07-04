import { shallowMount } from '@vue/test-utils';
import User from '@/components/User'

describe('User.vue', () => {
  it('should render Create User Button', () => {
    const wrapper = shallowMount(User);
    const contentButton = wrapper.find('button');
    expect(contentButton.text()).toEqual('Create User');
  })
})
