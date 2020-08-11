<template>
  <section class="section">
    <div class="buttons">
      <b-button
        type="is-success"
        expanded
        style="background-color: blueviolet"
        @click="(isInsertActive = true), getFilteredTags"
        >Insertar Tienda</b-button
      >
    </div>
    <b-tabs>
      <b-tab-item label="Tiendas del Sistema">
        <b-table :data="tiendjson" :hoverable="true">
          <template slot-scope="props">
            <template v-for="column in columns">
              <b-table-column :key="column.id" v-bind="column">
                <template
                  v-if="column.searchable"
                  slot="searchable"
                  slot-scope="props"
                >
                  <b-input
                    v-model="props.filters[props.column.field]"
                    placeholder="Buscar..."
                    icon="magnify"
                    size="is-small"
                  />
                </template>
                <template v-if="column.field == 'action'">
                  <b-button
                    type="is-info"
                    @click="
                      (isEditActive = true),
                        (editTiendN = props.row.nombre),
                        (editTiendTags = props.row.depart),
                        (editID = props.row.id_tienda),
                        getFilteredTags
                    "
                    ><b-icon icon="pencil" />
                  </b-button>
                  <b-button
                    type="is-danger"
                    @click="emitEliminarTiend(props.row.id_tienda)"
                  >
                    <b-icon icon="delete" />
                  </b-button>
                </template>
                <template v-if="column.field == 'departs'">
                  <p v-for="dapart in props.row.depart" :key="dapart.id">
                    {{ dapart.nombre }}
                  </p>
                </template>
                {{ props.row[column.field] }}
              </b-table-column>
            </template>
          </template>
        </b-table>
      </b-tab-item>
    </b-tabs>
    <b-modal :active.sync="isInsertActive">
      <form
        action
        v-on:submit.prevent="emitInsertTiend(), (isInsertActive = false)"
      >
        <div class="modal-card" style="width: auto">
          <header class="modal-card-head">
            <p class="modal-card-title">Insertar Tienda</p>
          </header>
          <section class="modal-card-body">
            <b-field label="Nombre">
              <b-input type="name" v-model="insertTiendName" required></b-input>
            </b-field>
            <b-field label="Enter some tags">
              <b-taginput
                v-model="tags"
                :data="filteredTags"
                autocomplete
                :allow-new="allowNew"
                :open-on-focus="openOnFocus"
                field="nombre"
                icon="label"
                placeholder="Add a tag"
              >
              </b-taginput>
            </b-field>
          </section>
          <footer class="modal-card-foot">
            <button class="button is-primary">
              Insertar
            </button>
          </footer>
        </div>
      </form>
    </b-modal>
    <b-modal :active.sync="isEditActive">
      <form
        action
        v-on:submit.prevent="emitEditTiend(editID), (isEditActive = false)"
      >
        <div class="modal-card" style="width: auto">
          <header class="modal-card-head">
            <p class="modal-card-title">Editar Tienda</p>
          </header>
          <section class="modal-card-body">
            <b-field label="Nombre">
              <b-input type="name" v-model="editTiendN" required></b-input>
            </b-field>
            <b-field label="Enter some tags">
              <b-taginput
                v-model="editTiendTags"
                :data="filteredTags"
                autocomplete
                :allow-new="allowNew"
                :open-on-focus="openOnFocus"
                field="nombre"
                icon="label"
                placeholder="Add a tag"
              >
              </b-taginput>
            </b-field>
          </section>
          <footer class="modal-card-foot">
            <button class="button is-primary">
              Editar
            </button>
          </footer>
        </div>
      </form>
    </b-modal>
  </section>
</template>

<script>
export default {
  props: {
    departjson: {
      type: Array
    },
    tiendjson: {
      type: Array
    }
  },

  computed: {
    getFilteredTags(text) {
      this.filteredTags = this.departjson.filter(option => {
        return option.nombre.toString();
      });
    }
  },

  data() {
    return {
      isInsertActive: false,
      insertTiendName: "",
      filteredTags: this.departjson,
      tags: [],
      openOnFocus: true,
      isSelectOnly: false,
      allowNew: false,
      isEditActive: false,
      editID: "",
      editTiendN: "",
      editTiendTags: [],
      columns: [
        {
          field: "id_tienda",
          label: "ID",
          width: "30",
          numeric: true
        },
        {
          field: "nombre",
          label: "Nombre",
          searchable: true
        },
        {
          field: "departs",
          label: "Departamentos"
        },
        {
          field: "action",
          label: ""
        }
      ]
    };
  },

  methods: {
    emitEliminarTiend(id) {
      this.$emit("emit-delete-tiend", { id });
    },

    emitInsertTiend() {
      var nombre = this.insertTiendName;
      var listDepart = this.tags;
      this.insertTiendName = "";
      this.tags = "";
      this.$emit("emit-insert-tiend", { nombre, listDepart });
    },

    emitEditTiend(id) {
      var nombre = this.editTiendN;
      var listDepart = this.editTiendTags;
      this.editTiendN = "";
      this.editTiendTags = [];
      this.$emit("emit-edit-tiend", { id, nombre, listDepart });
    }
  }
};
</script>
