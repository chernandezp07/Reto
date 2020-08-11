<template>
  <div
    class="column is-4 is-full-mobile is-half-tablet is-one-third-desktop is-one-quarter- widescreen"
  >
    <div class="card">
      <div class="card-content">
        <div class="media">
          <div class="media-left">
            <figure class="image is-48x48">
              <img src="../static/store.png" alt="Placeholder image" />
            </figure>
          </div>
          <div class="media-content">
            <p class="title is-5">{{ prodct.nombre }}</p>
            <p class="subtitle is-6">$ {{ prodct.precio }} CUC</p>
          </div>
          <div class="media-end">
            <b-button
              type="is-text button is-medium"
              @click="emitAddCar(prodct)"
            >
              <b-icon style="color: blueviolet" icon="cart-plus" />
            </b-button>
          </div>
        </div>
        <div class="content">
          {{ prodct.descripcion }}
          <p class="title is-6">Cantidad Disponible: {{ prodct.cantDisp }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    prodct: {
      type: Object
    }
  },
  data() {
    return {};
  },

  methods: {
    emitAddCar(prod) {
      this.$emit("emit-add-car", { prod });
      this.$buefy.toast.open({
        duration: 1000,
        message: `Producto agragado al carrito`
      });
    }
  }
};
</script>
