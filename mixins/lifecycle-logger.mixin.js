/**
 * 믹스인
 *
 * 컴포넌트를 사용하는 것 말고, 코드를 재사용할 수 있는 또 다른 방법이다.
 * Vue.js 는 Vue 컴포넌트의 options 객체에 믹스인을 혼합한다.
 * 이를 활용해 여러 컴포넌트에서 활용할 수 있는 기능을 만들 수 있다.
 *
 * 믹스인과 컴포넌트 모두 같은 옵션을 포함하는 경우 Vue.js 는 옵션의 값에 따라
 * 다른 전략으로 그것들을 병합한다.
 * 예를 들어 믹스인과 컴포넌트가 둘 다 created() 라이프 사이클 훅을 포함하면 Vue.js 는 두 메소드를
 * 배열에 넣고 믹스인의 메소드를 먼저 호출한다.
 *
 * 관심 있는 몇개의 훅을 정의해 Vue 인스턴스의 라이프 사이클을 살펴보는 lifecycle-logger 믹스인을 만들어본다.
 */
export default {
	created() {
		console.log(`${this.$options.name} created..`);
	},
	beforeMount() {
		console.log(`${this.$options.name} about to mount..`);
	},
	mounted() {
		console.log(`${this.$options.name} mounted..`);
	},
	destroyed() {
		console.log(`${this.$options.name} destroyed..`);
	},
	
}