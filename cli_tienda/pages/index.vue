<template>
  <section class="section">
    <div
      class="columns is-mobile is-1 is-multiline"
      v-if="tiendjson[0] != null"
    >
      <cardTiend
        v-for="tiend in tiendjson"
        :key="tiend.id"
        :tiend="tiend"
        icon="github"
      >
        <router-link
          :to="{ name: 'departTienda', params: { id_tienda: tiend.id_tienda } }"
        >
          Entrar</router-link
        >
      </cardTiend>
    </div>
    <div v-else>No hay Tiendas disponibles en el sitio.</div>
  </section>
</template>

<script>
import CardTiend from "~/components/CardTienda";

export default {
  components: {
    CardTiend
  },

  data() {
    return {
      tiendjson: []
    };
  },

  created() {
    this.$axios.get("/api/tienda").then(res => {
      this.tiendjson = res.data;
    });
  }
};
</script>
