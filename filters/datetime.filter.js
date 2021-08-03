/**
 * Vue.js 의 Filter 는 자바의 필터와 다름
 * Vue 애플리케이션에서는 필터를 이용해 이중 중괄호 보간법 또는 v-bind 표현법을 이용할 때 텍스트 형식을 지정한다.
 *
 * 필터는 표현식의 값을 첫번째 인자로 가지는 자바스크립트 함수이다.
 *
 * Vue.js 는 필터를 등록하는 두 가지 방법을 제공하며 Vue.filter() 로 전역으로 등록하거나
 * 컴포넌트의 options 객체의 filters 프로퍼티를 로컬로 등록할 수 있다.
 *
 * 규약에 따라 필터 파일을 filters 디렉토리 아래에 놓고
 * 파일명을 지시자의 이름과 유사하게
 * ${filterName}.filter.js 로 한다.
 *
 * 날짜와 시간 형식을 다루기 위해 Intel.DateTimeFormat 을 이용한다.
 *
 * 이거를 index.html 에 적용하고,
 * MessageListItem.js 에서 날짜에 파이프(|)로 분리한 형태로 필터를 추가한 것을 볼 수 있다.
 */
const formatter = Intl.DateTimeFormat('en-US', {
	year : 'numeric',
	month : 'long',
	week : 'long',
	day : 'numeric',
	hour : 'numeric',
	minute : 'numeric',
	second : 'numeric'
});
Vue.filter('datetime', function( value, pattern ) {
	if ( !value ) {
		return '';
	}
	return formatter.format( value );
});