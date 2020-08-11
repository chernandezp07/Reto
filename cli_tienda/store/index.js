export const state = () => ({
  product: []
});

export const mutations = {
  saveProduct(state, product) {
    state.product = product;
  },
  setListProduct(state, prod){
    var pos = state.product.indexOf(prod);
    if(pos === -1){
      state.product.push(prod);
    }

  }

};
