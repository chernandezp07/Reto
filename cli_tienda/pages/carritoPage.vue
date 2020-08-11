<template>
  <section class="sectionfrontt">
    <div class="buttons">
      <nuxt-link to="/" exact-active-class="is-active">
        <b-button
          type="is-success"
          style="background-color: blueviolet"
          expanded
          @click="comprar"
          >Realizar Compra</b-button
        >
      </nuxt-link>
    </div>
    <div v-if="listProdct[0] != null">
      <div class=" column is-12 is-mobile is-multiline">
        <CardProdCarrito
          v-for="prodcto in listProdct"
          :key="prodcto.id"
          :prodcto="prodcto"
          @emit-delete-car="deleteProd"
        ></CardProdCarrito>
      </div>
    </div>
    <div v-else>No hay productos en el carro</div>
  </section>
</template>

<script>
import CardProdCarrito from "~/components/CardProdCarrito";

export default {
  components: {
    CardProdCarrito
  },

  data() {
    return {
      listProdct: [],
      sms: ""
    };
  },

  mounted() {
    if (JSON.parse(localStorage.getItem("prodct"))[0] != null) {
      this.listProdct = JSON.parse(localStorage.getItem("prodct"));
    }
  },

  methods: {
    abc: function() {
      console.log(this.listProdct);
    },
    deleteProd: function(params) {
      console.log(params.prod);
      let pos = this.listProdct.indexOf(params.prod);
      console.log(pos);
      this.listProdct.splice(pos, 1);
      const parsed = JSON.stringify(this.listProdct);
      localStorage.setItem("prodct", parsed);
    },

    comprar: function() {
      if (JSON.parse(localStorage.getItem("prodct"))[0] != null) {
        this.$axios
          .put("/api/producto/compra", {
            lostProCompr: this.listProdct
          })
          .then(res => {
            {
              this.sms = res.data;
            }
          })
          .catch(err => {
            alert(Object.values(error.response.data));
            console.log(err);
          });
        const parsed = JSON.stringify([]);
        localStorage.setItem("prodct", parsed);
        this.$buefy.toast.open({
          duration: 2000,
          message: `Se ha realizado la compra`,
          position: "is-bottom",
          type: "is-success"
        });
      }
    }
  }
};
</script>

<style>
.sectionfrontt {
  padding-top: 0px !important;
  position: relative;
  width: 100%;
  height: 100%;
  background-size: cover;
  background-attachment: fixed;
  font-family: Roboto, sans-serif;
}
</style>
