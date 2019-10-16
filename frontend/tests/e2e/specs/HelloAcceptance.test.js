// For authoring Nightwatch tests, see
// https://nightwatchjs.org/guide

module.exports = {
    'default e2e tests': browser => {
        browser
            .init()
            .waitForElementVisible('#app')
            .assert.elementPresent('.hello')
            .assert.containsText('h1', 'Welcome to your Vue.js powered Spring Boot App')
            .assert.elementCount('img', 1)
            .end()
    },

    'example e2e test using a custom command': browser => {
        browser
            .openHomepage()
            .assert.elementPresent('.hello')
            .end()
    }
};

