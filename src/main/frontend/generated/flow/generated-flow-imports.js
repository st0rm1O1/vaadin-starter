import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/login/src/vaadin-login-overlay.js';
import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/vaadin-lumo-styles/color-global.js';
import '@vaadin/vaadin-lumo-styles/typography-global.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === '5754b39115aad015696d59ba325c6f84d565636910656899ec71e542e487dc3b') {
    pending.push(import('./chunks/chunk-0cbaba9acad000d46ef5d143e29b616fde49732f0f8fd8789b5c46d8dabf8e8b.js'));
  }
  return Promise.all(pending);
}

window.Vaadin = window.Vaadin || {};
window.Vaadin.Flow = window.Vaadin.Flow || {};
window.Vaadin.Flow.loadOnDemand = loadOnDemand;
window.Vaadin.Flow.resetFocus = () => {
 let ae=document.activeElement;
 while(ae&&ae.shadowRoot) ae = ae.shadowRoot.activeElement;
 return !ae || ae.blur() || ae.focus() || true;
}