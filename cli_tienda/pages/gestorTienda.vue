<template>
  <section>
    <div class="buttons">
      <p class="title is-4 " style="flex-grow: 1; justify-content: center;">
        Gestor de Tiendas
      </p>
      <b-field label="Cantidad de unidades por producto a comprar">
        <b-numberinput
          v-model="cantProdCarrit"
          min="1"
          :editable="false"
        ></b-numberinput>
      </b-field>
    </div>
    <b-tabs position="is-centered" class="block">
      <b-tab-item label="Tiendas">
        <tiendtable
          :tiendjson="tiendjson"
          :departjson="departjson"
          @emit-insert-tiend="insertTiend"
          @emit-edit-tiend="editTiend"
          @emit-delete-tiend="eliminarTiend"
        />
      </b-tab-item>
      <b-tab-item label="Departamentos">
        <departtable
          :productjson="productjson"
          :departjson="departjson"
          @emit-insert-depart="insertDepart"
          @emit-edit-depart="editDepart"
          @emit-delete-depart="eliminarDepart"
        />
      </b-tab-item>
      <b-tab-item label="Productos">
        <producttable
          :productjson="productjson"
          @emit-delete-prod="eliminarProduct"
          @emit-insert-prod="insertProduct"
          @emit-edit-prod="editProduct"
        />
      </b-tab-item>
    </b-tabs>
  </section>
</template>

<script>
import Producttable from "~/components/ProductTable";
import Departtable from "~/components/DepartTable";
import Tiendtable from "~/components/TiendaTable";

export default {
  components: {
    Producttable,
    Departtable,
    Tiendtable
  },

  data() {
    return {
      productjson: [],
      departjson: [],
      tiendjson: [],
      cantProdCarrit: 1
    };
  },

  created() {
    this.$axios.get("/api/producto").then(res => {
      this.productjson = res.data;
    });
    this.$axios.get("/api/departamento").then(res => {
      this.departjson = res.data;
    });
    this.$axios.get("/api/tienda").then(res => {
      this.tiendjson = res.data;
    });
  },

  mounted() {
    if (localStorage.cantProdCarrit) {
      this.cantProdCarrit = parseInt(localStorage.cantProdCarrit, 10);
    }
  },

  methods: {
    eliminarProduct: function(id) {
      this.$axios
        .delete("/api/producto/" + id.id)
        .then(res => {
          {
            this.productjson = res.data;
          }
        })
        .catch(err => {
          alert(Object.values(error.response.data));
          console.log(err);
        });
    },
    insertProduct: function(parm) {
      this.$axios
        .post("/api/producto", {
          nombre: parm.nombre,
          descripcion: parm.descripcion,
          precio: parm.precio,
          foto: parm.foto,
          cantDisp: parm.cantDisp
        })
        .then(res => {
          {
            this.productjson = res.data;
          }
        })
        .catch(err => {
          alert(Object.values(error.response.data));
          console.log(err);
        });
    },
    editProduct: function(parm) {
      this.$axios
        .put("/api/producto/" + parm.id, {
          nombre: parm.nombre,
          descripcion: parm.descripcion,
          precio: parm.precio,
          foto: parm.foto,
          cantDisp: parm.cantDisp
        })
        .then(res => {
          {
            this.productjson = res.data;
          }
        })
        .catch(err => {
          alert(Object.values(error.response.data));
          console.log(err);
        });
    },

    insertDepart: function(parm) {
      this.$axios
        .post("/api/departamento", {
          nombre: parm.nombre,
          product: parm.listProd
        })
        .then(res => {
          {
            this.departjson = res.data;
          }
        })
        .catch(err => {
          alert(Object.values(error.response.data));
          console.log(err);
        });
    },
    editDepart: function(parm) {
      this.$axios
        .put("/api/departamento/" + parm.id, {
          nombre: parm.nombre,
          product: parm.listProd
        })
        .then(res => {
          {
            this.departjson = res.data;
          }
        })
        .catch(err => {
          alert(Object.values(error.response.data));
          console.log(err);
        });
    },
    eliminarDepart: function(id) {
      this.$axios
        .delete("/api/departamento/" + id.id)
        .then(res => {
          {
            this.departjson = res.data;
          }
        })
        .catch(err => {
          alert(Object.values(error.response.data));
          console.log(err);
        });
    },

    insertTiend: function(parm) {
      this.$axios
        .post("/api/tienda", {
          nombre: parm.nombre,
          depart: parm.listDepart
        })
        .then(res => {
          {
            this.tiendjson = res.data;
          }
        })
        .catch(err => {
          alert(Object.values(error.response.data));
          console.log(err);
        });
    },
    editTiend: function(parm) {
      this.$axios
        .put("/api/tienda/" + parm.id, {
          nombre: parm.nombre,
          depart: parm.listDepart
        })
        .then(res => {
          {
            this.tiendjson = res.data;
          }
        })
        .catch(err => {
          alert(Object.values(error.response.data));
          console.log(err);
        });
    },
    eliminarTiend: function(id) {
      this.$axios
        .delete("/api/tienda/" + id.id)
        .then(res => {
          {
            this.tiendjson = res.data;
          }
        })
        .catch(err => {
          alert(Object.values(error.response.data));
          console.log(err);
        });
    }
  },

  watch: {
    cantProdCarrit(newCantd) {
      if (newCantd <= 0) {
        localStorage.cantProdCarrit = 1;
        this.cantProdCarrit = 1;
      } else {
        localStorage.cantProdCarrit = newCantd;
      }
    }
  }
};
</script>
