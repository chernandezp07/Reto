<template>
  <div class="column is-12 is-mobile is-multiline">
    <div class="card">
      <div class="card-content">
        <div class="card-header media">
          <div class="media-left">
            <figure class="image is-48x48">
              <img src="../static/store.png" alt="Placeholder image" />
            </figure>
          </div>
          <div class="media-content">
            <p class="title is-5">{{ prodcto.nombre }}</p>
            <p class="subtitle is-6">$ {{ prodcto.precio }} CUC</p>
          </div>
          <div class="media-end">
            <b-button type="is-danger" @click="emitDeleteCar(prodcto)">
              <b-icon icon="cart-off" />
            </b-button>
          </div>
        </div>
        <div class="content">
          {{ prodcto.descripcion }}
          <p class="title is-6">Cantidad Disponible: {{ prodcto.cantDisp }}</p>

          <b-field label="Cantidad de unidades a comprar">
            <b-numberinput
              class="end"
              v-model="cantProdComp"
              min="1"
              :max="maxValor"
              :editable="false"
            ></b-numberinput>
          </b-field>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    prodcto: {
      type: Object
    }
  },

  data() {
    return {
      cantProdCompAnt: 0,
      cantProdComp: 1,
      maxValor: 10
    };
  },

  mounted() {
    if (localStorage.cantProdCarrit) {
      this.maxValor = parseInt(localStorage.cantProdCarrit, 10);
    }
    this.prodcto.cantDisp--;
  },

  methods: {
    emitDeleteCar(prod) {
      this.$emit("emit-delete-car", { prod });
      this.$buefy.toast.open({
        duration: 1000,
        message: `El producto fue sacado del carrito`,
        position: "is-bottom",
        type: "is-danger"
      });
    }
  },

  watch: {
    cantProdComp(newCantd) {
      if (newCantd > this.cantProdCompAnt) {
        this.prodcto.cantDisp--;
        this.cantProdCompAnt++;
      } else {
        this.prodcto.cantDisp++;
        this.cantProdCompAnt--;
      }
      if (this.prodcto.cantDisp == 0) {
        this.maxValor = this.prodcto.cantDisp;
        console.log("Entro al if");
      } else {
        this.maxValor = parseInt(localStorage.cantProdCarrit, 10);
        console.log("Entro aki");
      }
    }
  }
};
</script>
