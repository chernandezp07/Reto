<template>
  <section class="sectionfront">
    <b-navbar class="is-link" :fixed-top="true" :transparent="false">
      <template slot="start">
        <div style="flex-grow: 1; justify-content: center;">
          <nuxt-link class="navbar-item" to="/">
            <h1 class="title is-3 color-text">Tiendas La Habana</h1>
          </nuxt-link>
        </div>
      </template>
      <template slot="end">
        <div class="navbar-item">
          <div class="buttons">
            <nuxt-link
              class="button"
              to="/carritoPage"
              exact-active-class="is-active"
            >
              <b-icon class="colorClass" icon="cart-arrow-right" />
            </nuxt-link>
            <nuxt-link
              class="button"
              to="/gestorTienda"
              exact-active-class="is-active"
            >
              <b-icon class="colorClass" icon="cog" />
            </nuxt-link>
          </div>
        </div>
      </template>
    </b-navbar>
    <div class="section container">
      <section class="main-content columns">
        <div class="container column is-10 section">
          <nuxt />
        </div>
      </section>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {};
  },

  mounted() {
    if (!localStorage.cantProdCarrit) {
      localStorage.cantProdCarrit = 1;
    }
    let scrollpos = window.scrollY;
    const header = document.querySelector("nav");
    const header_height = header.offsetHeight;
    const add_class_on_scroll = () => header.classList.add("isActive");
    const remove_class_on_scroll = () => header.classList.remove("isActive");
    window.addEventListener("scroll", function() {
      scrollpos = window.scrollY;
      if (scrollpos >= header_height) {
        add_class_on_scroll();
        console.log(add_class_on_scroll);
      } else {
        remove_class_on_scroll();
        console.log(remove_class_on_scroll);
      }
    });
  }
};
</script>

<style>
nav.navbar.is-fixed-top {
  transition: background-color 0.5s ease;
  background: transparent;
}
nav.navbar.is-fixed-top.isActive {
  transition: background-color 0.5s ease;
  background: black;
}
.color-text {
  color: rgb(121, 87, 213);
}
body {
  background: linear-gradient(to top, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.55));
  padding-top: 0px !important;
}
.colorClass {
  color: blueviolet;
}
</style>
